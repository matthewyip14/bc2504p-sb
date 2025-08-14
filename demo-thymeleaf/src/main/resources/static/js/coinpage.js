// coinpage.js - Styles and functionality for coinpage.html

document.addEventListener('DOMContentLoaded', function() {
  // Apply styles dynamically
  applyStyles();
  
  // Initialize coin filter functionality
  initializeCoinFilter();
});

function applyStyles() {
  // Create and inject CSS styles
  const style = document.createElement('style');
  style.type = 'text/css';
  
  const css = `
    /* Global font styling */
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      font-size: 14px;
      line-height: 1.6;
      color: #333;
    }
    
    /* Header styling */
    h1 {
      font-family: 'Arial', sans-serif;
      font-size: 28px;
      font-weight: bold;
      color: #2c3e50;
      text-align: center;
    }
    
    /* Table styling */
    table {
      font-family: Arial, sans-serif;
      border-collapse: collapse;
      width: 100%;
    }
    
    th {
      font-weight: bold;
      font-size: 16px;
      background-color: #4a54be;
      padding: 12px;
      text-align: left;
    }
    
    td {
      padding: 10px;
      font-size: 14px;
    }
    
    /* Input field styling */
    .form-control {
      font-family: inherit;
      font-size: 14px;
      padding: 8px;
      margin: 10px 0;
    }
  `;
  
  if (style.styleSheet) {
    // For IE
    style.styleSheet.cssText = css;
  } else {
    style.appendChild(document.createTextNode(css));
  }
  
  // Append to head
  document.head.appendChild(style);
}

function initializeCoinFilter() {
  // Coin filter functionality
  var coinFilter = document.getElementById('coinFilter');
  var coinRows = document.querySelectorAll('.coinRow');

  if (coinFilter && coinRows.length > 0) {
    coinFilter.addEventListener('input', function() {
      var filter = coinFilter.value.toUpperCase();
      coinRows.forEach(function(row) {
        var coinId = row.querySelector('td:nth-child(2)').textContent.toUpperCase();
        if (coinId.indexOf(filter) > -1) {
          row.style.display = '';
        } else {
          row.style.display = 'none';
        }
      });
    });
  }
}