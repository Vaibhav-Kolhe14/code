<?php
include 'db.php';
$id = $_GET['id'];
$result = $conn->query("SELECT * FROM students WHERE id=$id");
$row = $result->fetch_assoc();
?>

<h2>Edit Student</h2>
<form method="POST" action="update.php">
  <input type="hidden" name="id" value="<?= $row['id']; ?>">
  Name: <input type="text" name="name" value="<?= $row['name']; ?>"><br><br>
  Email: <input type="email" name="email" value="<?= $row['email']; ?>"><br><br>
  <input type="submit" value="Update">
</form>
