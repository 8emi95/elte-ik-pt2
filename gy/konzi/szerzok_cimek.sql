SELECT firstName, lastName, titles.ISBN, title
FROM authors
INNER JOIN authorISBN ON authors.authorID = authorISBN.authorID
INNER JOIN titles ON authorISBN.ISBN = titles.ISBN