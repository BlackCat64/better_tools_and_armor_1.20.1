import json
import tkinter as tk
from tkinter import filedialog

def main():
    try:
        new_efficiency = float(input("Enter the new efficiency value: "))
    except ValueError:
        print("Invalid number entered.")
        return

    # Hide the root Tk window
    root = tk.Tk()
    root.withdraw()

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

            if "definition" in data:
                tool_settings = data["definition"]
                
                if "efficiency" in tool_settings:
                    old_value = tool_settings["efficiency"]
                    tool_settings["efficiency"] = new_efficiency

                    with open(file_path, "w", encoding="utf-8") as f:
                        json.dump(data, f, indent=2)

                    print(f"Updated {file_path}: {old_value} → {new_efficiency}")
                    changed = changed + 1
            else:
                print(f"Skipped {file_path}: no 'efficiency' field found")
            

    print(f"Done. {changed} / {total} successfully updated.")

if __name__ == "__main__":
    main()
