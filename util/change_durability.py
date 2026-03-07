import json
import tkinter as tk
from tkinter import filedialog

def main():
    try:
        new_durability = float(input("Enter the new Base Crystallite Tool durability value: "))
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
    changed_tools = 0
    changed_armor = 0
    for file_path in file_paths:
            with open(file_path, "r", encoding="utf-8") as f:
                data = json.load(f)

            # Only process Crystallite items
            if "_type" in data and "Crystallite" in file_path:
                elem_type = data["_type"]

                if elem_type == "tool":
                    if "definition" in data:
                        tool_settings = data["definition"]

                        if "usageCount" in tool_settings:
                            old_value = tool_settings["usageCount"]

                            new_value = int(new_durability)

                            if ("Sculk" in file_path) or ("Netherite" in file_path):
                                new_value = int(new_value * 1.25)
                            elif "Diamond" in file_path and (not "Nether" in file_path):
                                new_value = int(new_value * 2)
                            
                            if "Dagger" in file_path:
                                new_value = int(new_value * 0.75)

                            tool_settings["usageCount"] = new_value

                            with open(file_path, "w", encoding="utf-8") as f:
                                json.dump(data, f, indent=2)

                            print(f"Updated {file_path}: {old_value} → {new_value}")
                            changed_tools = changed_tools + 1
                        else:
                            print(f"Skipped {file_path}: no 'usageCount' field found")

                elif elem_type == "armor":
                    if "definition" in data:
                        armor_settings = data["definition"]

                        if "maxDamage" in armor_settings:
                            old_value = armor_settings["maxDamage"]

                            # Hardcode armor durability values
                            new_value = 0
                            if ("Sculk" in file_path) or ("Netherite" in file_path):
                                new_value = 50
                            elif "Diamond" in file_path and (not "Nether" in file_path):
                                new_value = 80
                            else:
                                new_value = 40

                            armor_settings["maxDamage"] = new_value

                            print(f"Updated {file_path}: {old_value} → {new_value}")
                            changed_armor = changed_armor + 1
                        else:
                            print(f"Skipped {file_path}: no 'maxDamage' field found")
            
    print(f"Done. {changed_tools} tools successfully updated.")
    print(f"{changed_armor} armors successfully updated.")

if __name__ == "__main__":
    main()
