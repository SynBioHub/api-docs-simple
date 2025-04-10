const fs = require('fs');
const path = require('path');
const yaml = require('js-yaml');

// Directory containing OpenAPI specs
const OPENAPI_DIR = path.join(__dirname, '../src/openapi');
// Output file for sidebar JSON data
const OUTPUT_FILE = path.join(__dirname, '../src/sidebar-data.json');

// Read and parse all OpenAPI spec files
async function readSpecs() {
  const specs = [];
  
  try {
    const files = fs.readdirSync(OPENAPI_DIR);
    
    for (const file of files) {
      if (file.endsWith('.yaml') || file.endsWith('.yml')) {
        const filePath = path.join(OPENAPI_DIR, file);
        const content = fs.readFileSync(filePath, 'utf8');
        
        try {
          const spec = yaml.load(content);
          const id = path.basename(file, path.extname(file));
          
          specs.push({
            id,
            title: spec.info.title || id,
            description: spec.info.description || '',
            version: spec.info.version || '1.0.0',
            order: getOrderFromFilename(id)
          });
        } catch (e) {
          console.error(`Error parsing ${file}:`, e);
        }
      }
    }
  } catch (err) {
    console.error('Error reading OpenAPI directory:', err);
  }
  
  // Sort specs by order and then title
  specs.sort((a, b) => {
    if (a.order !== b.order) return a.order - b.order;
    return a.title.localeCompare(b.title);
  });
  
  return specs;
}

// Extract order from filename if it contains a number prefix
function getOrderFromFilename(filename) {
  const match = filename.match(/^(\d+)-/);
  return match ? parseInt(match[1], 10) : 999;
}

// Main function
async function main() {
  console.log('Reading OpenAPI specs...');
  const specs = await readSpecs();
  console.log(`Found ${specs.length} specifications`);
  
  console.log(`Writing sidebar data to ${OUTPUT_FILE}`);
  fs.writeFileSync(OUTPUT_FILE, JSON.stringify(specs, null, 2), 'utf8');
  
  console.log('Done!');
}

main();
