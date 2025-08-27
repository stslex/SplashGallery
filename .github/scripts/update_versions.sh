#!/bin/bash

# Define the initial script directory
script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
project_dir="$(dirname "$script_dir")"
project_dir="$(dirname "$project_dir")"
echo "Project script directory: $project_dir"

# Define the path to the target file
version_file="$project_dir/gradle/libs.versions.toml"

# Check if the target file exists
if [ -f "$version_file" ]; then
    echo "Target file found: $version_file"
else
    echo "Target file not found"
    exit 1  # Exit the script if the target file is not found
fi

# Read versionName and versionCode from the file
version_name=$(awk -F '"' '/versionName = "/{print $2}' "$version_file")
version_code=$(awk -F '"' '/versionCode = "/{print $2}' "$version_file")

# Increment versionCode by 1
((version_code++))

# Increment versionName by 0.01
version_name=$(echo "$version_name + 0.01" | bc)

# Update the file with the new version information
sed "s/versionName = \".*\"/versionName = \"$version_name\"/" "$version_file" > "$version_file.tmp"
mv "$version_file.tmp" "$version_file"

sed "s/versionCode = \".*\"/versionCode = \"$version_code\"/" "$version_file" > "$version_file.tmp"
mv "$version_file.tmp" "$version_file"

echo "Updated versionName to $version_name and versionCode to $version_code in $version_file"

