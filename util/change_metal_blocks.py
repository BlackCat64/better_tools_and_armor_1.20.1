import json
import tkinter as tk
from tkinter import filedialog

# Everything Search filter:
# polished content:block !content:recipe

# Read mapping file from old to new names
# Loop through all selected files
# Find files matching the filter (Contains Polished, has Block elem type)
# Change 'name' to new name
# Change 'specialInformation' to { "fixedValue": [] }

def main():

    # Hide the root Tk window
    root = tk.Tk()
    root.withdraw()

    name_map = {} # Empty dictionary
    mapping_file = filedialog.askopenfilenames(title="Select name mapping file")
    with open(mapping_file[0], "r", encoding="utf-8") as f:
        txt = f.read()
        lines = txt.split("\n")
        for line in lines:
            tokens = line.split("\t")
            name_map[tokens[0]] = tokens[1]

    # Open file selection dialog
    file_paths = filedialog.askopenfilenames(
        title="Select MCreator JSON files",
        filetypes=[("JSON files", "*.json")]
    )

    if not file_paths:
        print("No files selected.")
        return

    total = len(file_paths)
    changed = 0
    for file_path in file_paths:
            with open(file_path, "r", encoding="utf-8") as f:
                data = json.load(f)

            # Only process 'Polished' Metal Blocks
            if "_type" in data and "Polished" in file_path:
                elem_type = data["_type"]

                if elem_type == "block":
                    if "definition" in data:
                        settings = data["definition"]

                        if "name" in settings:
                            old_value = settings["name"]

                            new_value = ""
                            for n in name_map:
                                if n in old_value:
                                    new_value = old_value.replace(n, name_map[n])
                                    break

                            if len(new_value) > 0:
                                settings["name"] = new_value

                            if "specialInformation" in settings:
                                settings["specialInformation"] = {}
                                print("Removed tooltip")

                            with open(file_path, "w", encoding="utf-8") as f:
                                json.dump(data, f, indent=2)

                            print(f"Updated {file_path}: {old_value} → {new_value}")
                            changed = changed + 1
                        else:
                            print(f"Skipped {file_path}: no 'name' field found")
            
    print(f"Done. {changed} tools successfully updated.")

if __name__ == "__main__":
    main()
