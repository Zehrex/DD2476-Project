import javalang
import json
import os


class Indexer:
    def __init__(self):
        self.index = dict()
        self.count = 0
        self.dir = './content'
        self.language = 'java'
        self.json = None

    def execute(self):

        def get_return_type(current_node):
            if current_node.return_type is not None:
                res = current_node.return_type.name
                if not isinstance(current_node.return_type, javalang.tree.BasicType):
                    if current_node.return_type.arguments is not None:
                        res += '<'
                        for arg in current_node.return_type.arguments:
                            res += arg.type.name + ','
                        res = res[:-1] + '>'
                return res
            else:
                return 'void'

        idx = 1
        for filename in os.listdir(self.dir):
            print('file #' + str(idx) + '\r', end='')
            idx += 1
            with open(self.dir + '/' + filename, 'r', encoding='utf-8-sig') as file:
                url = file.readline()[:-1]  # .decode('utf-8')
                metadata = {'url': url}
                code = file.read()
                try:
                    tree = javalang.parse.parse(code)
                except javalang.parser.JavaSyntaxError:
                    continue
                for path, node in tree.filter(javalang.tree.ClassDeclaration):
                    metadata['class'] = {
                        'name': node.name,
                        'extends': None if node.extends is None else node.extends.name,
                        'implements': None if not node.implements else [node.implements[i].name for i in
                                                                        range(len(node.implements))],
                        'modifiers': list(node.modifiers)
                    }
                    for child in node.body:
                        if isinstance(child, javalang.tree.MethodDeclaration):
                            self.index[self.count] = {
                                'method_name': child.name,
                                'throws': child.throws,
                                'modifiers': list(child.modifiers),
                                'return_type': get_return_type(child),
                            }
                            self.index[self.count].update(metadata)
                            self.count += 1

        with open('index', 'w') as json_file:
            json.dump(self.index, json_file)


indexer = Indexer()
indexer.execute()
