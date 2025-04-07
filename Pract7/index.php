<?php include 'db.php'; ?>

<h2>Add Student</h2>
<form method="POST" action="add.php">
  Name: <input type="text" name="name" required><br><br>
  Email: <input type="email" name="email" required><br><br>
  <input type="submit" value="Add">
</form>

<h2>Student List</h2>
<table border="1" cellpadding="10">
  <tr><th>ID</th><th>Name</th><th>Email</th><th>Actions</th></tr>
  <?php
  $result = $conn->query("SELECT * FROM students");
  while($row = $result->fetch_assoc()):
  ?>
    <tr>
      <td><?= $row['id']; ?></td>
      <td><?= $row['name']; ?></td>
      <td><?= $row['email']; ?></td>
      <td>
        <a href="edit.php?id=<?= $row['id']; ?>">Edit</a> |
        <a href="delete.php?id=<?= $row['id']; ?>">Delete</a>
      </td>
    </tr>
  <?php endwhile; ?>
</table>
