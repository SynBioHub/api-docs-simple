openapi-generator generate \
    -i plugin-template.yaml \
    -g spring \
    -o ./generated \
    --additional-properties=useSpringBoot3=true,interfaceOnly=true,apiPackage=org.synbiohub.api,modelPackage=org.synbiohub.model