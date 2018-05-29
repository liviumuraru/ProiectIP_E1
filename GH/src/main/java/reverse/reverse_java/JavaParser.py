import os
import sys
import dict2uml
import json
import plantuml
import shutil
from string import rfind

try:
    root_dir = sys.argv[1]
except Exception as err:
    print "Exception caught:", err
    print "Try passing an argument"
    exit()


if not os.path.exists(root_dir):
    print "Invalid path"
    exit()

def get_info():
    classes = []
    interfaces = []
    implementations = []
    
    found = 0
    relations = []
    found_list = []

    for folder, dirs, files in os.walk(root_dir):
        for file in files:
            if file.endswith('.java'):
                if (found == 0):
                    fldr = folder
                found = 1
                full_path = os.path.join(folder, file)
                with open(full_path, 'r') as f:
                    for line in f:
                        if "class " in line and "class constructor" not in line:
#                             print line
                            class_line = line.split()[2]
#                             print class_line
                            classes += [class_line]

                            temp_dict = {class_line : {}}

                            if "extends" in line:
                                for name in line.split()[4:]:
                                    if name not in "\{":
                                        implementations += [name.replace(',', '')]
                                for name in implementations:
                                    if name in found_list:
                                        pass
                                    else:
                                        impl_dict = {name:temp_dict}
                                        found_list.append(name)
                                        relations.append(impl_dict)
                            else:
                                relations.append(temp_dict)
                                found_list.append(class_line)
                
    relations.append(fldr)                 
    return relations

def move_photos(folder):
    dir_path = os.path.dirname(os.path.realpath(__file__)) 
    for files in os.walk(dir_path):
        for file in files:
            for f in file:
                if f.endswith('.png'):
                    path = os.path.join(dir_path, f)
                    path_to_move = os.path.join(folder, f)
                    shutil.move(path, path_to_move)        
                

if __name__ == "__main__":
    info = get_info()
    folder = info[-1]
    
    print folder
    info.pop()
    
    uml = plantuml.PlantUML()
    uml_no = 1
    for d in info:
        with open(str(uml_no) + '.png', 'wb') as out:
            out.write(uml.processes(dict2uml.dict2plantuml(d)))
        uml_no += 1
         
    move_photos(folder)
        
