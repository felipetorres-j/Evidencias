package com.example.myapplication

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_crear.*

class MenuSlideActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fm: FragmentManager
    var us:String=""
    var bun_us:Bundle= Bundle()
    lateinit var crearFragment: CrearFragment
    lateinit var lista_EvFragment: Lista_EvFragment
    lateinit var homeFragment: HomeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_slide)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        var bundle = intent.extras
        us = bundle?.getString("dt")!!
        Toast.makeText(this,"user:"+ us,Toast.LENGTH_LONG).show()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_slide, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        fm = supportFragmentManager //con esto vamos a poder cambiar a los fragmentos
        //enviar user a fragment

        bun_us.putString("user",us)


        //crearFragment = CrearFragment() // le indicamos que contiene al fragmento crear
        when (item.itemId) {

            R.id.nav_home -> {
                //Toast.makeText(this,"HOME", Toast.LENGTH_LONG).show()
                crearFragment = CrearFragment()
                crearFragment.arguments = bun_us
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.menuid,crearFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
                //fm.beginTransaction().replace(R.id.menuid, CrearFragment()).commit()
                // Handle the camera action

            }
            R.id.nav_gallery -> {
                //crearFragment = CrearFragment()
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.menuid,homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
                //fm.beginTransaction().replace(R.id.escenario, CrearFragment()).commit()
            }
            R.id.nav_slideshow -> {
                    lista_EvFragment = Lista_EvFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.menuid,lista_EvFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
}
