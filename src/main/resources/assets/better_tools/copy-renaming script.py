import os
import shutil

def get_file_path(prompt):
    path = input(prompt).strip()
    while not os.path.isfile(path):
        print("Invalid file path. Please try again.")
        path = input(prompt).strip()
    return path

def get_word(prompt):
    word = input(prompt).strip()
    while not word:
        print("Invalid input. Please enter a word.")
        word = input(prompt).strip()
    return word

def replace_word_in_file(src_path, dest_path, old_word, new_word):
    with open(src_path, 'r', encoding='utf-8') as src_file:
        content = src_file.read()
    content = content.replace(old_word, new_word)
    with open(dest_path, 'w', encoding='utf-8') as dest_file:
        dest_file.write(content)

def main():
    word_list_path = get_file_path("Enter the path to the word list file: ")
    source_file_path = get_file_path("Enter the path to the file to be copied: ")
    word_to_replace = get_word("Enter the word to be replaced: ")
    
    with open(word_list_path, 'r', encoding='utf-8') as word_list_file:
        words = [line.strip() for line in word_list_file if line.strip()]
    
    for word in words:
        new_file_name = source_file_path.replace(word_to_replace, word)
        dest_file_path = os.path.join(os.path.dirname(source_file_path), new_file_name)
        
        shutil.copy(source_file_path, dest_file_path)
        replace_word_in_file(source_file_path, dest_file_path, word_to_replace, word)
        print(f"Created: {dest_file_path}")
    input()

if __name__ == "__main__":
    main()
