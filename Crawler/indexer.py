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
                return "void"

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

        def get_code_snippet (lines, index):
            openingBrackets = 0;
            closingBrackets = 0;

            # Resulting snippet
            snippet = ""

            # loop until we find the final closing bracket, or go over the limit
            while index < len(lines):
                line = lines[index]
                for letter in line:
                    if letter == '}':
                        closingBrackets += 1
                    elif letter == '{':
                        openingBrackets += 1

                    snippet += letter

                    # Early end if we have reached last bracket
                    if closingBrackets == openingBrackets and openingBrackets != 0:
                        return snippet

                index += 1

            return snippet

        idx = 1
        numDocuments = 0
        for filename in os.listdir(self.dir):
            print('file #' + str(idx) + '\n')
            print(filename)
            idx += 1
            with open(self.dir + '/' + filename, 'r', encoding='utf-8-sig') as file:
                stars = file.readline()[:-1]
                url = file.readline()[:-1]  # .decode('utf-8')
                metadata = {'url': url, 'stars': stars}
                code = file.read()
                lines = code.split("\n")
                try:
                    tree = javalang.parse.parse(code)
                except javalang.parser.JavaSyntaxError:
                    continue
                except javalang.tokenizer.LexerError:
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
                            numDocuments += 1
                            if numDocuments % 5000 == 0:
                                with open('index' + str(numDocuments // 5000) + '.json', 'w') as json_file:
                                    json.dump(self.index, json_file)
                                self.index = []
                            self.index.append({
                                'method_name': child.name,
                                'params': get_params_type(child.parameters),
                                'throws': child.throws,
                                'modifiers': list(child.modifiers),
                                'return_type': get_return_type(child),
                                'snippet': get_code_snippet (lines, child.position[0] - 1)
                            })
                            self.index[-1].update(metadata)
                            self.count += 1

        with open('index' + str(numDocuments // 5000 + 1) + '.json', 'w') as json_file:
            json.dump(self.index, json_file)


indexer = Indexer()
indexer.execute()
