import json
import tkinter as tk
from tkinter import filedialog

def main():
    # Hide the root Tk window
    root = tk.Tk()
    root.withdraw()

    # Open file selection dialog
    files = filedialog.askopenfilenames(
        title="Select MCreator JSON files",
        filetypes=[("JSON files", "*.json")]
    )

    if not files:
        print("No files selected.")
        return

    total = len(files)
    changed = 0
    for file_path in files:
            with open(file_path, "r", encoding="utf-8") as f:
                data = json.load(f)

            elem_type = data["_type"]
            if not (elem_type == "block" and "Polished" in file_path): # Only remove tabs from 'Polished' blocks
                continue

            if "definition" in data:
                block_settings = data["definition"]
                
                if "creativeTabs" in block_settings:
                    block_settings["creativeTabs"] = []

                    with open(file_path, "w", encoding="utf-8") as f:
                        json.dump(data, f, indent=2)

                    print(f"Updated {file_path}")
                    changed = changed + 1
                else:
                    print(f"Skipped {file_path}: no 'creativeTabs' field found")
            

    print(f"Done. {changed} / {total} successfully updated.")

if __name__ == "__main__":
    main()
