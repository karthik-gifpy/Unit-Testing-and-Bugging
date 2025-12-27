# run-tests.ps1
# Downloads Apache Maven if not present and runs `mvn test verify` for the project.

param(
    [string]$ProjectDir = "$(Resolve-Path .)"
)

function Run-Maven($mvnCmd) {
    Write-Host "Running tests with: $mvnCmd";
    & $mvnCmd -f "$ProjectDir\pom.xml" test verify
    return $LASTEXITCODE
}

# Check for mvn on PATH
$mvn = Get-Command mvn -ErrorAction SilentlyContinue
if ($mvn) {
    Write-Host "Found mvn at $($mvn.Path)";
    exit (Run-Maven $mvn.Path)
}

# Not found: download Maven
$version = "3.9.6"
$baseUrl = "https://dlcdn.apache.org/maven/maven-3/$version/binaries"
$zipName = "apache-maven-$version-bin.zip"
$downloadUrl = "$baseUrl/$zipName"
$temp = [System.IO.Path]::GetTempPath()
$zipPath = Join-Path $temp $zipName
$extractDir = Join-Path $temp "apache-maven-$version"

Write-Host "mvn not found. Will download Apache Maven $version to $zipPath"

if (-Not (Test-Path $zipPath)) {
    Invoke-WebRequest -Uri $downloadUrl -OutFile $zipPath -UseBasicParsing
}

if (Test-Path $extractDir) { Remove-Item $extractDir -Recurse -Force }
Expand-Archive -Path $zipPath -DestinationPath $temp -Force

$mvnCmd = Join-Path $extractDir "bin\mvn.cmd"
if (-Not (Test-Path $mvnCmd)) {
    Write-Error "Downloaded Maven, but mvn.cmd not found at $mvnCmd"
    exit 2
}

# Run tests
$exit = Run-Maven $mvnCmd
exit $exit
