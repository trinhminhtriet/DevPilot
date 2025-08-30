{
  "kind": "collectionType",
  "collectionName": "github_users",
  "info": {
    "singularName": "${entityName}",
    "pluralName": "${entityName}s",
    "displayName": "Github User"
  },
  "options": {
    "draftAndPublish": true
  },
  "pluginOptions": {},
  "attributes": {
    "username": {
      "type": "string",
      "unique": true
    },
    "profile_url": {
      "type": "string"
    },
    "email": {
      "type": "email"
    },
    "fullname": {
      "type": "string"
    },
    "avatar": {
      "type": "string"
    },
    "bio": {
      "type": "text"
    },
    "intro": {
      "type": "text"
    },
    "company": {
      "type": "string"
    },
    "location": {
      "type": "string"
    },
    "repositories_count": {
      "type": "integer",
      "default": 0
    },
    "followed_at": {
      "type": "datetime"
    }
  }
}
