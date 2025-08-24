# Python .gitattributes
*.py linguist-language=Python
requirements.txt linguist-language=Python
setup.py linguist-language=Python

# Normalize line endings
* text=auto

# Prevent generated files from polluting diffs
__pycache__/ linguist-generated=true
*.pyc linguist-generated=true
*.pyo linguist-generated=true
*.egg linguist-generated=true
*.eggs linguist-generated=true

# Optional: treat documentation as text
*.md text

# Optional: ignore formatting files
*.editorconfig linguist-generated=true
