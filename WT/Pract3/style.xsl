<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/employees">
    <html>
      <head>
        <title>Employee List</title>
        <style>
          table { border-collapse: collapse; width: 60%; margin: auto; }
          th, td { border: 1px solid #000; padding: 8px; text-align: center; }
          th { background-color: #f2f2f2; }
        </style>
      </head>
      <body>
        <h2 style="text-align:center;">Employee Details</h2>
        <table>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Department</th>
            <th>Salary</th>
          </tr>
          <xsl:for-each select="employee">
            <tr>
              <td><xsl:value-of select="id"/></td>
              <td><xsl:value-of select="name"/></td>
              <td><xsl:value-of select="department"/></td>
              <td><xsl:value-of select="salary"/></td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
