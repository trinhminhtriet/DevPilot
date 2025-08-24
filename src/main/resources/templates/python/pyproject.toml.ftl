# pyproject.toml for Python project
[project]
name = "${projectName}"
version = "0.1.0"
description = "${description!default('A Python project')}"
authors = [
    { name = "${author.name!default('Your Name')}", email = "${author.email!default('your@email.com')}" }
]
readme = "README.md"
requires-python = ">=3.8"
dependencies = [
    # Add your dependencies here
]

[build-system]
requires = ["setuptools>=61.0", "wheel"]
build-backend = "setuptools.build_meta"
