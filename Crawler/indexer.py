import javalang
import json
import os


class Indexer:
    def __init__(self):
        self.index = []
        self.count = 0
        self.dir = './data'
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
                            if arg.type is not None:
                                res += arg.type.name + ','
                            else:
                                res += '?,'
                        res = res[:-1] + '>'
                return res
            else:
                return None

        def get_params_type(params):
            res = []
            if params:
                for param in params:
                    if isinstance(param.type, javalang.tree.BasicType):
                        res.append(param.type.name)
                    else:
                        param_type = param.type.name
                        if param.type.arguments is not None:
                            param_type += '<'
                            for arg in param.type.arguments:
                                if arg.type is not None:
                                    param_type += arg.type.name + ','
                                else:
                                    param_type += '?,'
                            param_type = param_type[:-1] + '>'
                        res.append(param_type)
            return res

        idx = 1
        for filename in os.listdir(self.dir):
            print('file #' + str(idx) + '\n')
            print(filename)
            idx += 1
            with open(self.dir + '/' + filename, 'r', encoding='utf-8-sig') as file:
                stars = file.readline()[:-1]
                url = file.readline()[:-1]  # .decode('utf-8')
                metadata = {'url': url, 'stars': stars}
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
                            self.index.append({
                                'id': self.count,
                                'method_name': child.name,
                                'params': get_params_type(child.parameters),
                                'throws': child.throws,
                                'modifiers': list(child.modifiers),
                                'return_type': get_return_type(child),
                            })
                            self.index[-1].update(metadata)
                            self.count += 1

        with open('index', 'w') as json_file:
            json.dump(self.index, json_file)


indexer = Indexer()
indexer.execute()
