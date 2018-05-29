"""
Testing file for c#.py, will be used for verifying the output of various
running cases. Run each test independently by calling the test's function name.
"""
import os
from subprocess import check_output
import ast

"""
:param proj_dir -> running directory
:param python_path -> path where Python2.7 is installed
"""
proj_dir = os.getcwd()  # "C:\\Users\\cpoenaru\\Documents\\GitHub\\Facultate\\ProiectIPM3\\reverse_c#"
python_path = "C:\Python27"


def convert_to_list(string):
    """
    Converts a string to list, in order to compare the outputs.
    Used in all tests from here below.
    >>convert_to_list("TestString")
    ["TestString]
    """
    return ast.literal_eval(string.decode('utf-8').strip())


def check_good_file():
    """
    Test used for checking if a file has the expected output.
    Runs the c#.py script on the file and compares the output with a hardcoded one.
    The file is a correct .cs file with classes, implementations etc
    The script should detect all the classes.
    >>check_good_file()
    1
    0
    """
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "EasyHttp")])
    expected_output = [{'Exception': {'ConfigurationException:': {}}}, {'FileData': {}}, {':': {'HttpException': {}}},
                       {'class': {}}, {'PropertyValue': {}}, {'ObjectToUrl': {'ObjectToUrlParameters': {}}},
                       {'class': {}}, {'UriComposer': {}}]

    actual_output = convert_to_list(out)

    return actual_output == expected_output


def check_good_file_bad_output():
    """
    Test used for checking if a file has the expected output.
    Runs the c#.py script on the file and compares the output with a hardcoded one.
    The file is a correct .cs file with classes, implementations etc
    The expected output (hardcoded) is wrong and it should be detected.
    The script should detect all the classes.
    >>check_good_file_bad_output()
    1
    0
    """
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "EasyHttp")])
    expected_output = [{'Exception': {'ConfigurationException:': {}}}, {'FileData': {}}, {':': {'HttpException': {}}},
                       {'class': {}}, {'PropertyValue': {}}, {'ObjectToUrl': {'ObjectToUrlParameters': {}}},
                       {'class': {}}]

    actual_output = convert_to_list(out)

    return actual_output != expected_output


def check_no_class():
    """
    Test used for checking if a file has the expected output.
    Runs the c#.py script on the file and compares the output with a hardcoded one.
    The file is a correct .cs file but it has no classes at all. The script should detect no classes.
    >>check_no_class()
    1
    0
    """
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "Tests\\no_class")])
    out_list = out.split('\n')[0]
    expected_output = []

    actual_output = convert_to_list(out_list)

    return actual_output == expected_output


def check_invalid_directory():
    """
    Test used for checking if trying to parse an invalid directory works.
    Runs the c#.py script in the directory and that script should detect that the folder is invalid.
    There is no .cs file. The script should detect no classes.
    >>check_invalid_directory()
    1
    0
    """
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "Tests\\invalid_directory")])
    out = out.split('\n')[0]
    actual_output = convert_to_list(out)
    expected_output = []

    return actual_output == expected_output


def check_empty_directory():
    """
    Test used for checking if trying to parse an empty directory works.
    Runs the c#.py script in the empty directory and the script should detect that the folder is empty.
    There is no .cs file. The script should detect no classes.
    >>check_empty_directory()
    1
    0
    """
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "Tests\\empty_dir")])
    out = out.split('\n')[0]
    actual_output = convert_to_list(out)
    expected_output = []

    return actual_output == expected_output


def check_invalid_file():
    """
    Test used for checking if a file has the expected output.
    Runs the c#.py script on the file and compares the output with a hardcoded one.
    The file is invalid, as it's a .png file with its extension changed to .cs.
    The script should detect no classes,
    >>check_invalid_file()
    1
    0
    """
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "Tests\\png_file")])
    out_list = out.split('\n')[0]
    actual_output = convert_to_list(out_list)
    expected_output = []

    return actual_output == expected_output and "No images found" in out


def check_empty_file():
    """
    Test used for checking if a file has the expected output.
    Runs the c#.py test on the file and compares the output with a hardcoded one.
    The file is empty and the script should detect no classes.
    >>check_empty_file()
    1
    0
    """
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "Tests\\empty_file")])
    out_list = out.split('\n')[0]
    actual_output = convert_to_list(out_list)
    expected_output = []

    return actual_output == expected_output and "No images found" in out


def main():
    """
    The main function calls all the test functions and prints wether the test
    passed or it failed. It may take some time for the first test to run.
    >>main()
    i. [PASSED/FAILED] [...]
    i = 1, 8
    """
    if check_good_file():
        print "1. [PASSED] Good file was parsed correctly!"
    else:
        print "1. [FAILED] Good file was parsed incorrectly!"

    if check_good_file_bad_output():
        print "2. [PASSED] File was parsed correctly, bad output detected!"
    else:
        print "[FAILED] File was parsed incorrectly!"

    if check_invalid_directory():
        print "3. [PASSED] Invalid directory was parsed correctly!"
    else:
        print "3. [FAILED] Invalid directory was parsed incorrectly!"

    if check_empty_directory():
        print "4. [PASSED] Invalid directory was parsed correctly!"
    else:
        print "4. [FAILED] Invalid directory was parsed incorrectly!"

    if check_invalid_file():
        print "5. [PASSED] Invalid file was parsed correctly!"
    else:
        print "6. [FAILED] Invalid file was parsed incorrectly!"

    if check_empty_file():
        print "7. [PASSED] Empty file was parsed correctly!"
    else:
        print "7. [FAILED] Empty file was parsed incorrectly!"

    if check_no_class():
        print "8. [PASSED] File with no classes was parsed correctly!"
    else:
        print "8. [FAILED] File with no classes was parsed incorrectly!"


if __name__ == "__main__":
    main()
