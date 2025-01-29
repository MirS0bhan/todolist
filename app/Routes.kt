sealed class Routes(val route: String) {
    object main : Routes("Login")
    object add : Routes("Signup")
}