function showSelectedRole() {
    var dropdown = document.getElementById("roleDropdown");
    var selectedRole = dropdown.value;
    console.log(selectedRole)
    var label = document.getElementById("selectedRole");
    label.textContent = selectedRole ? "Selected Role: " + selectedRole : "";

    document.getElementById("hiddenRole").value = selectedRole;
}