from github import Github
import re
import os


class Crawler:
    def __init__(self, language, page,per_page, access_token=None, user_name=None, password=None):
        """
        :param page: number of pages
        :param per_page: repos per page
        """
        if access_token is None:
            self.client = Github(user_name, password)
        else:
            self.client = Github(access_token)
        self.args = {'language': language, 'page': page, 'per_page': per_page}

    def execute(self):
        g = self.client
        print("Rate Limit: ", g.get_rate_limit())
        language = self.args['language']
        page = self.args['page']
        per_page = self.args['per_page']

        access_rights = 0o755
        dir = './data'

        if not os.path.exists(dir):
            try:
                os.mkdir(dir, access_rights)
            except OSError:
                print("Cannot create directory X|")

        repositories = g.search_repositories(query='language:' + language, page=page, per_page=per_page)
        for repo in repositories:
            print(repo.name)
            contents = repo.get_contents("")  # get content for the whole repo
            while contents:
                # print("Rate Limit: ", g.get_rate_limit())
                file_content = contents.pop(0)
                if file_content.type == "dir":
                    contents.extend(repo.get_contents(file_content.path))
                elif re.search(".*\." + language + "$", file_content.name):
                    dc = file_content.decoded_content
                    with open(dir + "/" + file_content.name, 'w+b') as file:
                        print(file_content.name)
                        print(file_content.download_url)
                        file.write(bytearray(str(repo.stargazers_count) + '\n', 'utf-8'))
                        file.write(bytearray(file_content.download_url+'\n', 'utf-8'))
                        file.write(dc)


# generate your own access token if this one does not work or use your user name and password 
at = 'c2f760d1d65f8229f16b2728cbeea04f7ec016d4'
crawler = Crawler(access_token=at, language='java', page=10, per_page=100)
crawler.execute()
