<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Listing</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet">
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
    </script>
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="d-flex justify-content-end mb-4">
        <a href="bookcart" class="btn btn-primary">
            View Cart
        </a>
    </div>

    <div class="row g-4">
        <c:forEach items="${books}" var="b">
            <div class="col-12 col-sm-6 col-lg-4">
                <div class="card h-100 shadow-sm">
                    <img src="images/${b.imgUrl}" class="card-img-top" alt="${b.title}" style="height: 200px; object-fit: cover;">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">${b.title}</h5>
                        <p class="card-text mb-1"><strong>Author:</strong> ${b.author}</p>
                        <p class="card-text mb-3"><strong>Price:</strong> $${b.price}</p>

                        <form action="${pageContext.request.contextPath}/bookcart" method="post" class="mt-auto">
                            <div class="mb-3 d-flex align-items-center">
                                <label for="quantity-${b.id}" class="form-label me-2 mb-0">Quantity:</label>
                                <input type="number" id="quantity-${b.id}" name="quantity" value="1" min="1" class="form-control w-25">
                            </div>
                            <input type="hidden" name="id" value="${b.id}">
                            <input type="hidden" name="price" value="${b.price}">
                            <input type="hidden" name="title" value="${b.title}">
                            <input type="hidden" name="author" value="${b.author}">
                            <input type="hidden" name="action" value="add">

                            <div class="d-flex gap-2">
                                <button type="submit" name="addToCart" class="btn btn-success w-50">Add To Cart</button>
                                <a href="${pageContext.request.contextPath}/books?id=${b.id}" class="btn btn-outline-secondary w-50">
                                    Book Detail
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
