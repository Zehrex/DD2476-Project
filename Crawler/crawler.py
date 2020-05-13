

from github import Github
import re
import os
import time
import datetime


class Crawler:
    def __init__(self, language="java", access_token=None, user_name=None, password=None):
        """
        :param language: language of the files to retrieve
        """
        if access_token is None:
            self.client = Github(user_name, password)
        else:
            self.client = Github(access_token)
        self.args = {'language': language}

    def update_file(self, to_date, retrieved):

        """ Updates the text file that keeps track of retrieved repositories and the last week that was accessed"""

        try:
            fp = open('max_date_retrieved.txt', "w+")
            fp.write(to_date.isoformat() + '\n')
            fp.write(str(retrieved))
        finally:
            fp.close()

    def read_file(self):

        """ Reads the text file that keeps track of total retrieved repositories and the last week that was accessed"""

        try:
            fp = open('max_date_retrieved.txt')
            max_date = fp.readline()
            retrieved = fp.readline()
            if max_date != "" and retrieved != "":
                max_date = datetime.datetime.strptime(max_date, "%Y-%m-%dT%H:%M:%S").date()
                retrieved = int(retrieved)
        finally:
            fp.close()
        # if the file was empty or we did not manage to load it, just fill with defaults
        if (max_date is None) or (max_date is ""):
            max_date = datetime.datetime.now().date()
        if retrieved is None or retrieved is "":
            retrieved = 0
        return max_date, retrieved


    def retrieve_repos(self):

        """ Keeps yielding more and more repositories for the crawler to process """

        g = self.client
        language = self.args['language']
        max_date, retrieved = self.read_file()
        to_date = max_date
        from_date = max_date - datetime.timedelta(days=7)
        rate_limit = g.get_rate_limit()
        while rate_limit.search.remaining > 0:
            week_of_repos = g.search_repositories(query='language:' + language,
                                                  sort='stars',
                                                  created=from_date.isoformat() + ".." + to_date.isoformat())
            print("we just retrieved " + str(week_of_repos.totalCount) + " repos")
            print("rate limit: " + str(rate_limit.search))
            retrieved += week_of_repos.totalCount
            to_date -= datetime.timedelta(days=7)
            from_date -= datetime.timedelta(days=7)
            rate_limit = g.get_rate_limit()
            self.update_file(to_date, retrieved) # the retrieved count might not be correct
            yield week_of_repos

        print("In total " + retrieved + " repos were retrieved. Current rate limit remaining is "
              + rate_limit.search.remaining)

    def execute(self):
        """Iterates over the retrieved methods, filters out java files and saves them in the data folder"""
        g = self.client
        repo_count = 1
        language = self.args['language']
        access_rights = 0o755
        dir = './data'
        if not os.path.exists(dir):
            try:
                os.mkdir(dir, access_rights)
            except OSError:
                print("Cannot create directory X|")
        for page in self.retrieve_repos():
            for repo in page:
                # [optional] TODO
                #  figure out why this page.totalCount(~50335)
                #  is different from the same page.totalCount in retrieve_repos(1000)
                print(str(repo_count) + " / " + str(page.totalCount) + " repo name: " + repo.name)
                repo_count += 1
                contents = repo.get_contents("")  # get content for the whole repo
                while contents:
                    rate_limit = g.get_rate_limit()
                    if rate_limit.core.remaining > 0:
                        file_content = contents.pop(0)
                        if file_content.type == "dir":
                            contents.extend(repo.get_contents(file_content.path))
                        elif re.search(".*\." + language + "$", file_content.name):
                            dc = file_content.decoded_content
                            with open(dir + "/" + file_content.name, 'w+b') as file:
                                print("Rate Limit: ", g.get_rate_limit())
                                print(file_content.name)
                                file.write(bytearray(str(repo.stargazers_count) + '\n', 'utf-8'))
                                file.write(bytearray(file_content.download_url + '\n', 'utf-8'))
                                file.write(dc)
                    else:
                        # if out of rate limit, lets wait until we have more
                        reset_secs = rate_limit.core.reset
                        print("We wait "+ reset_secs/60 + "minutes")
                        time.sleep(reset_secs)

        # Current design now shouldnt let the crawler finalize
        # execution by itself since we have "infinite supply" of code
        print("Done executing")


# generate your own access token if this one does not work or use your user name and password 
at = 'c2f760d1d65f8229f16b2728cbeea04f7ec016d4'
crawler = Crawler(access_token=at, language='java')
crawler.execute()
