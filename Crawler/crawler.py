from github import Github
import re
import os


class Crawler:
    def __init__(self, language, N_max, access_token=None, user_name=None, password=None):
        if access_token is None:
            self.client = Github(user_name, password)
        else:
            self.client = Github(access_token)
        self.args = {'language': language, 'N_max': N_max}

    def execute(self):
        g = self.client
        language = self.args['language']
        N_max = self.args['N_max']
        repositories = g.search_repositories(query='language:' + language)

        access_rights = 0o755
        dir = './content'
        if not os.path.exists(dir):
            try:
                os.mkdir(dir, access_rights)
            except OSError:
                print("Cannot create directory X|")

        for repo in repositories:
            contents = repo.get_contents("")  # get content for the whole repo
            n = 0
            while contents and n < N_max:
                file_content = contents.pop(0)
                if file_content.type == "dir":
                    contents.extend(repo.get_contents(file_content.path))
                elif re.search(".*\." + language + "$", file_content.name):
                    n += 1
                    dc = file_content.decoded_content
                    with open(dir + "/" + file_content.name, 'w+b') as file:
                        print(file_content.name)
                        print(file_content.download_url)
                        file.write(bytearray(file_content.download_url+'\n', 'utf-8'))
                        file.write(dc)

# generate your own access token if this one does not work or use your user name and password 
access_token = '447802bcab23780e6f6b10dcf6adce7f5d555710'
crawler = Crawler(access_token=access_token, language='java', N_max=100)
crawler.execute()
