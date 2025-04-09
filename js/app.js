document.addEventListener('DOMContentLoaded', function() {
  // First load the sidebar data, then initialize the rest of the app
  loadSidebarData().then(initializeApp);
  
  function loadSidebarData() {
    return fetch('./sidebar-data.json')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to load sidebar data');
        }
        return response.json();
      })
      .then(data => {
        // Build the sidebar dynamically
        const sidebarNav = document.querySelector('.sidebar nav ul');
        sidebarNav.innerHTML = ''; // Clear any existing content
        
        data.forEach(item => {
          const li = document.createElement('li');
          const a = document.createElement('a');
          a.setAttribute('href', '#');
          a.setAttribute('data-api', item.id);
          a.setAttribute('title', item.description);
          a.textContent = item.title;
          li.appendChild(a);
          sidebarNav.appendChild(li);
        });
        
        return data; // Return the data for further use
      })
      .catch(error => {
        console.error('Error loading sidebar data:', error);
        return []; // Return empty array in case of error
      });
  }
  
  function initializeApp(sidebarData) {
    // Initialize with the first API spec or fallback to user-endpoints
    const defaultApi = sidebarData.length > 0 ? sidebarData[0].id : 'user-endpoints';
    loadApiSpec(defaultApi);
  
    // Mobile menu toggle functionality
    const mobileMenuButton = document.querySelector('.mobile-menu-button');
    const sidebar = document.querySelector('.sidebar');
    const sidebarOverlay = document.querySelector('.sidebar-overlay');
    
    mobileMenuButton.addEventListener('click', function() {
      sidebar.classList.toggle('active');
      sidebarOverlay.classList.toggle('active');
      document.body.classList.toggle('sidebar-open');
    });
    
    // Close sidebar when clicking outside or on a link (mobile only)
    sidebarOverlay.addEventListener('click', function() {
      sidebar.classList.remove('active');
      sidebarOverlay.classList.remove('active');
      document.body.classList.remove('sidebar-open');
    });
  
    // Set up navigation
    const navLinks = document.querySelectorAll('.sidebar nav a');
    navLinks.forEach(link => {
      // Mark the first link as active initially
      if (link.getAttribute('data-api') === defaultApi) {
        link.classList.add('active');
      }
      
      link.addEventListener('click', function(e) {
        e.preventDefault();
        
        // Remove active class from all links
        navLinks.forEach(l => l.classList.remove('active'));
        
        // Add active class to clicked link
        this.classList.add('active');
        
        // Load the selected API spec
        const apiName = this.getAttribute('data-api');
        loadApiSpec(apiName);
        
        // Close sidebar on mobile after clicking a link
        if (window.innerWidth <= 768) {
          sidebar.classList.remove('active');
          sidebarOverlay.classList.remove('active');
          document.body.classList.remove('sidebar-open');
          document.querySelector('.content-area').scrollIntoView({ behavior: 'smooth' });
        }
      });
    });
  }

  // Keep track of the Swagger UI instance
  let swaggerUIInstance = null;

  function loadApiSpec(apiName) {
    // Update the example commands based on the selected API
    updateExampleCommands(apiName);
    
    // Check if the API spec file exists
    fetch(`./openapi/${apiName}.yaml`)
      .then(response => {
        if (!response.ok) {
          throw new Error(`API spec file not found: ${apiName}.yaml`);
        }
        return response.url;
      })
      .then(url => {
        // Completely remove the swagger-ui div and recreate it to avoid DOM conflicts
        const swaggerContainer = document.querySelector('#swagger-ui');
        const parentElement = swaggerContainer.parentElement;
        
        // Remove the old container
        swaggerContainer.remove();
        
        // Create a new container
        const newSwaggerContainer = document.createElement('div');
        newSwaggerContainer.id = 'swagger-ui';
        parentElement.insertBefore(newSwaggerContainer, parentElement.querySelector('.example-commands'));
        
        // Initialize Swagger UI with the API spec
        swaggerUIInstance = SwaggerUIBundle({
          url: url,
          dom_id: '#swagger-ui',
          deepLinking: true,
          presets: [
            SwaggerUIBundle.presets.apis,
            SwaggerUIBundle.SwaggerUIStandalonePreset
          ],
          layout: "BaseLayout",
          supportedSubmitMethods: [], // Disable Try it out feature
          syntaxHighlight: {
            activated: true,
            theme: "agate"
          }
        });
      })
      .catch(error => {
        console.error(error);
        
        // Safely handle errors by recreating the container
        const swaggerContainer = document.querySelector('#swagger-ui');
        if (swaggerContainer) {
          // Clear the container safely
          swaggerContainer.innerHTML = '';
          // Add error message
          const errorDiv = document.createElement('div');
          errorDiv.className = 'error-message';
          errorDiv.textContent = `Error loading API specification: ${error.message}`;
          swaggerContainer.appendChild(errorDiv);
        }
        
        // Reset the Swagger UI instance
        swaggerUIInstance = null;
      });
  }

  function updateExampleCommands(apiName) {
    const exampleCommands = document.querySelector('.example-commands');
    let examples;
    
    // Set different example commands based on the selected API
    switch (apiName) {
      case 'user-endpoints':
        examples = `<h4>User API Examples</h4>
          <p>Login:</p>
          <pre><code>curl -X POST -H "Accept: text/plain" -d "email=user@example.com&password=yourpassword" https://synbiohub.org/login</code></pre>
          <p>View profile (requires token):</p>
          <pre><code>curl -X GET -H "Accept: text/plain" -H "X-authorization: YOUR_TOKEN" https://synbiohub.org/profile</code></pre>`;
        break;
      case 'download-plugin':
        examples = `<h4>Download Plugin Examples</h4>
          <p>Download SBOL:</p>
          <pre><code>curl -X GET -H "Accept: text/plain" https://synbiohub.org/public/igem/BBa_K1404008/1/sbol</code></pre>
          <p>Download GenBank:</p>
          <pre><code>curl -X GET -H "Accept: text/plain" https://synbiohub.org/public/igem/BBa_K1404008/1/gb</code></pre>`;
        break;
      case 'visualization-plugin':
        examples = `<h4>Visualization Plugin Examples</h4>
          <p>Search for components:</p>
          <pre><code>curl -X GET -H "Accept: text/plain" "https://synbiohub.org/search/objectType=ComponentDefinition&limit=10"</code></pre>`;
        break;
      case 'submission-plugin':
        examples = `<h4>Submission Plugin Examples</h4>
          <p>Submit a new collection (requires token):</p>
          <pre><code>curl -X POST -H "Accept: text/plain" -H "X-authorization: YOUR_TOKEN" -F id=mycollection -F version=1 -F name="My Collection" -F description="A test collection" -F file=@sbol_file.xml https://synbiohub.org/submit</code></pre>`;
        break;
      default:
        examples = `<h4>Example Commands</h4>
          <p>Test the API with curl:</p>
          <pre><code>curl -X GET "https://synbiohub.org/public/igem/BBa_K1404008/1" -H "accept: application/json"</code></pre>`;
    }
    
    exampleCommands.innerHTML = examples;
  }
});
