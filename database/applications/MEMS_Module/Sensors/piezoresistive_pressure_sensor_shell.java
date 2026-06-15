/*
 * piezoresistive_pressure_sensor_shell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:04 by COMSOL 6.3.0.290. */
public class piezoresistive_pressure_sensor_shell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");
    model.component("comp1").physics().create("ecis", "ElectricCurrentsShell", "geom1");
    model.component("comp1").physics("ecis").prop("DefaultDescription")
         .set("DefaultDescription", "Electric_currents_in_shells");
    model.component("comp1").physics("ecis").prop("LayerSelection").set("lth_mat", "userdef");
    model.component("comp1").physics("ecis").create("pzrs1", "PiezoresistiveShell");
    model.component("comp1").physics("ecis").feature("pzrs1").selection().all();

    model.component("comp1").multiphysics().create("pzrs1", "PiezoresistiveEffectShell", 2);
    model.component("comp1").multiphysics("pzrs1").set("Solid_physics", "shell");
    model.component("comp1").multiphysics("pzrs1").set("ElectricCurrents_physics", "ecis");
    model.component("comp1").multiphysics("pzrs1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ecis", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/pzrs1", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1")
         .insertFile("piezoresistive_pressure_sensor_shell_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("difsel1");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u538b\u654f\u7535\u963b\u5668");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(9);
    model.component("comp1").selection().duplicate("sel2", "sel1");
    model.component("comp1").selection("sel2").label("\u8fde\u63a5");
    model.component("comp1").selection("sel2").set(3, 5, 6, 8, 13, 14, 15, 18);
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u9694\u819c");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("xmin", -501);
    model.component("comp1").selection("box1").set("xmax", 501);
    model.component("comp1").selection("box1").set("ymin", -30);
    model.component("comp1").selection("box1").set("ymax", 1000);
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u6a21\u578b\u8fb9\u754c");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "box1"});
    model.component("comp1").selection().duplicate("uni2", "uni1");
    model.component("comp1").selection("uni2").label("\u7535\u6d41");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel2", "sel1"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u56fa\u5b9a\u8fb9");
    model.component("comp1").selection("adj1").set("entitydim", 2);
    model.component("comp1").selection("adj1").set("input", new String[]{"box1"});
    model.component("comp1").selection("adj1").set("outputdim", 1);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"uni1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"box1"});
    model.component("comp1").selection("dif1").label("\u56fa\u5b9a\u8fb9\u754c");

    model.component("comp1").coordSystem().create("sys2", "Rotated");
    model.component("comp1").coordSystem("sys2").set("angle", new String[]{"-45[deg]", "0", "0"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup().create("Anisotropic", "Anisotropic", "Anisotropic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("AnisotropicVoGrp", "AnisotropicVoGrp", "Anisotropic, Voigt notation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("PiezoresistanceForm", "PiezoresistanceForm", "Piezoresistance form");
    model.component("comp1").material("mat1").propertyGroup("PiezoresistanceForm").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoresistanceForm", "ElastoresistanceForm", "Elastoresistance form");
    model.component("comp1").material("mat1").propertyGroup("ElastoresistanceForm").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").label("n-Silicon (single-crystal, lightly doped)");
    model.component("comp1").material("mat1")
         .comments("C. S. Smith, \u201cPiezoresistance Effect in Silicon and Germanium\u201d, Physical Review, vol. 94, no. 1, pp. 42-49, 1957.\n\nC. Jacoboni, C. Canali, G. Ottaviani and A. Alberigi Quaranta, \u201cA Review of some Charge Transport Properties of Silicon\u201d, Solid-State Electronics, vol. 20, pp. 77-89, 1977.");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "sigma0");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "(N*e_const*0.1400/sqrt(1+N/(N/350 +3e22)))");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"N"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("plotaxis", new String[]{"off"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"N", "", ""}});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2330[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma0(nd[m^3])", "0", "0", "0", "sigma0(nd[m^3])", "0", "0", "0", "sigma0(nd[m^3])"});
    model.component("comp1").material("mat1").propertyGroup("def").addInput("numberdensity");
    model.component("comp1").material("mat1").propertyGroup("Anisotropic").label("Anisotropic");
    model.component("comp1").material("mat1").propertyGroup("Anisotropic")
         .set("D", new String[]{"166[GPa]", "64[GPa]", "64[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "64[GPa]", "166[GPa]", "64[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "64[GPa]", "64[GPa]", "166[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", 
         "0[GPa]", "80[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("AnisotropicVoGrp").label("Anisotropic, Voigt notation");
    model.component("comp1").material("mat1").propertyGroup("AnisotropicVoGrp")
         .set("DVo", new String[]{"166[GPa]", "64[GPa]", "64[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "64[GPa]", "166[GPa]", "64[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "64[GPa]", "64[GPa]", "166[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", 
         "0[GPa]", "80[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("PiezoresistanceForm").label("Piezoresistance form");
    model.component("comp1").material("mat1").propertyGroup("PiezoresistanceForm").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("PiezoresistanceForm").func("an1")
         .set("funcname", "sigma0");
    model.component("comp1").material("mat1").propertyGroup("PiezoresistanceForm").func("an1")
         .set("expr", "(N*e_const*0.1400/sqrt(1+N/(N/350 +3e22)))");
    model.component("comp1").material("mat1").propertyGroup("PiezoresistanceForm").func("an1")
         .set("args", new String[]{"N"});
    model.component("comp1").material("mat1").propertyGroup("PiezoresistanceForm").func("an1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("PiezoresistanceForm").func("an1")
         .set("plotaxis", new String[]{"off"});
    model.component("comp1").material("mat1").propertyGroup("PiezoresistanceForm").func("an1")
         .set("plotfixedvalue", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("PiezoresistanceForm").func("an1")
         .set("plotargs", new String[][]{{"N", "", ""}});
    model.component("comp1").material("mat1").propertyGroup("PiezoresistanceForm")
         .set("Pil", new String[]{"-102.2e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "53.4e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "53.4e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "53.4e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "-102.2e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "53.4e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", 
         "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "53.4e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "53.4e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "-102.2e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", 
         "0[\u03a9*m/Pa]", "-13.6e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "-13.6e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", 
         "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "-13.6e-11[1/Pa]/sigma0(nd[m^3])[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("PiezoresistanceForm").addInput("numberdensity");
    model.component("comp1").material("mat1").propertyGroup("ElastoresistanceForm").label("Elastoresistance form");
    model.component("comp1").material("mat1").propertyGroup("ElastoresistanceForm").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoresistanceForm").func("an1")
         .set("funcname", "sigma0");
    model.component("comp1").material("mat1").propertyGroup("ElastoresistanceForm").func("an1")
         .set("expr", "(N*e_const*0.1400/sqrt(1+N/(N/350 +3e22)))");
    model.component("comp1").material("mat1").propertyGroup("ElastoresistanceForm").func("an1")
         .set("args", new String[]{"N"});
    model.component("comp1").material("mat1").propertyGroup("ElastoresistanceForm").func("an1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("ElastoresistanceForm").func("an1")
         .set("plotaxis", new String[]{"off"});
    model.component("comp1").material("mat1").propertyGroup("ElastoresistanceForm").func("an1")
         .set("plotfixedvalue", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("ElastoresistanceForm").func("an1")
         .set("plotargs", new String[][]{{"N", "", ""}});
    model.component("comp1").material("mat1").propertyGroup("ElastoresistanceForm")
         .set("ml", new String[]{"-101.4/sigma0(nd[m^3])[S/m]", "57.6/sigma0(nd[m^3])[S/m]", "57.6/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "57.6/sigma0(nd[m^3])[S/m]", "-101.4/sigma0(nd[m^3])[S/m]", "57.6/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", 
         "0[\u03a9*m]", "0[\u03a9*m]", "57.6/sigma0(nd[m^3])[S/m]", "57.6/sigma0(nd[m^3])[S/m]", "-101.4/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", 
         "0[\u03a9*m]", "-10.8/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "-10.8/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", 
         "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "-10.8/sigma0(nd[m^3])[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoresistanceForm").addInput("numberdensity");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup().create("Anisotropic", "Anisotropic", "Anisotropic");
    model.component("comp1").material("mat2").propertyGroup()
         .create("AnisotropicVoGrp", "AnisotropicVoGrp", "Anisotropic, Voigt notation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("PiezoresistanceForm", "PiezoresistanceForm", "Piezoresistance form");
    model.component("comp1").material("mat2").propertyGroup("PiezoresistanceForm").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElastoresistanceForm", "ElastoresistanceForm", "Elastoresistance form");
    model.component("comp1").material("mat2").propertyGroup("ElastoresistanceForm").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").label("p-Silicon (single-crystal, lightly doped)");
    model.component("comp1").material("mat2")
         .comments("C. S. Smith, \u201cPiezoresistance Effect in Silicon and Germanium\u201d, Physical Review, vol. 94, no. 1, pp. 42-49, 1957.\n\nC. Jacoboni, C. Canali, G. Ottaviani and A. Alberigi Quaranta, \u201cA Review of some Charge Transport Properties of Silicon\u201d, Solid-State Electronics, vol. 20, pp. 77-89, 1977.");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "sigma0");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "(N*e_const*0.048/sqrt(1+N/(N/81 +4e22)))");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"N"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("plotaxis", new String[]{"off"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"N", "", ""}});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2330[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma0(nd[m^3])", "0", "0", "0", "sigma0(nd[m^3])", "0", "0", "0", "sigma0(nd[m^3])"});
    model.component("comp1").material("mat2").propertyGroup("def").addInput("numberdensity");
    model.component("comp1").material("mat2").propertyGroup("Anisotropic").label("Anisotropic");
    model.component("comp1").material("mat2").propertyGroup("Anisotropic")
         .set("D", new String[]{"166[GPa]", "64[GPa]", "64[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "64[GPa]", "166[GPa]", "64[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "64[GPa]", "64[GPa]", "166[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", 
         "0[GPa]", "80[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("AnisotropicVoGrp").label("Anisotropic, Voigt notation");
    model.component("comp1").material("mat2").propertyGroup("AnisotropicVoGrp")
         .set("DVo", new String[]{"166[GPa]", "64[GPa]", "64[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "64[GPa]", "166[GPa]", "64[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "64[GPa]", "64[GPa]", "166[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", 
         "0[GPa]", "80[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("PiezoresistanceForm").label("Piezoresistance form");
    model.component("comp1").material("mat2").propertyGroup("PiezoresistanceForm").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("PiezoresistanceForm").func("an1")
         .set("funcname", "sigma0");
    model.component("comp1").material("mat2").propertyGroup("PiezoresistanceForm").func("an1")
         .set("expr", "(N*e_const*0.048/sqrt(1+N/(N/81 +4e22)))");
    model.component("comp1").material("mat2").propertyGroup("PiezoresistanceForm").func("an1")
         .set("args", new String[]{"N"});
    model.component("comp1").material("mat2").propertyGroup("PiezoresistanceForm").func("an1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("PiezoresistanceForm").func("an1")
         .set("plotaxis", new String[]{"off"});
    model.component("comp1").material("mat2").propertyGroup("PiezoresistanceForm").func("an1")
         .set("plotfixedvalue", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("PiezoresistanceForm").func("an1")
         .set("plotargs", new String[][]{{"N", "", ""}});
    model.component("comp1").material("mat2").propertyGroup("PiezoresistanceForm")
         .set("Pil", new String[]{"6.6e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "-1.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "-1.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "-1.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "6.6e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "-1.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", 
         "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "-1.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "-1.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "6.6e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", 
         "0[\u03a9*m/Pa]", "138.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "138.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", 
         "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "138.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("PiezoresistanceForm").addInput("numberdensity");
    model.component("comp1").material("mat2").propertyGroup("ElastoresistanceForm").label("Elastoresistance form");
    model.component("comp1").material("mat2").propertyGroup("ElastoresistanceForm").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("ElastoresistanceForm").func("an1")
         .set("funcname", "sigma0");
    model.component("comp1").material("mat2").propertyGroup("ElastoresistanceForm").func("an1")
         .set("expr", "(N*e_const*0.048/sqrt(1+N/(N/81 +4e22)))");
    model.component("comp1").material("mat2").propertyGroup("ElastoresistanceForm").func("an1")
         .set("args", new String[]{"N"});
    model.component("comp1").material("mat2").propertyGroup("ElastoresistanceForm").func("an1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("ElastoresistanceForm").func("an1")
         .set("plotaxis", new String[]{"off"});
    model.component("comp1").material("mat2").propertyGroup("ElastoresistanceForm").func("an1")
         .set("plotfixedvalue", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("ElastoresistanceForm").func("an1")
         .set("plotargs", new String[][]{{"N", "", ""}});
    model.component("comp1").material("mat2").propertyGroup("ElastoresistanceForm")
         .set("ml", new String[]{"9.6/sigma0(nd[m^3])[S/m]", "1.8/sigma0(nd[m^3])[S/m]", "1.8/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "1.8/sigma0(nd[m^3])[S/m]", "9.6/sigma0(nd[m^3])[S/m]", "1.8/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", 
         "0[\u03a9*m]", "0[\u03a9*m]", "1.8/sigma0(nd[m^3])[S/m]", "1.8/sigma0(nd[m^3])[S/m]", "9.6/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", 
         "0[\u03a9*m]", "110.0/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "110.0/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", 
         "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "110.0/sigma0(nd[m^3])[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoresistanceForm").addInput("numberdensity");
    model.component("comp1").material("mat2").selection().named("uni2");

    model.component("comp1").physics("shell").selection().named("uni1");
    model.component("comp1").physics("shell").feature("to1").set("d", "20[um]");
    model.component("comp1").physics("shell").feature("emm1").set("SolidModel", "Anisotropic");
    model.component("comp1").physics("shell").feature("emm1").set("AnisotropicOption", "AnisotropicVo");
    model.component("comp1").physics("shell").feature("emm1").feature("shls1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("shell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("shell").feature("fix1").selection().named("adj1");
    model.component("comp1").physics("shell").create("fix2", "Fixed", 2);
    model.component("comp1").physics("shell").feature("fix2").selection().named("dif1");
    model.component("comp1").physics("shell").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("shell").feature("fl1").selection().named("box1");
    model.component("comp1").physics("shell").feature("fl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("shell").feature("fl1").set("pressure", "100[kPa]");
    model.component("comp1").physics("ecis").selection().set();
    model.component("comp1").physics("ecis").selection().named("uni2");
    model.component("comp1").physics("ecis").prop("LayerSelection").set("lth", "0.4[um]");
    model.component("comp1").physics("ecis").feature("csh1").set("minput_numberdensity", "1.45e20[1/cm^3]");
    model.component("comp1").physics("ecis").feature("csh1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("ecis").feature("pzrs1").selection().named("sel1");
    model.component("comp1").physics("ecis").feature("pzrs1").set("minput_numberdensity", "1.32e19[1/cm^3]");
    model.component("comp1").physics("ecis").feature("pzrs1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("ecis").create("bgnd1", "BoundaryGround", 1);
    model.component("comp1").physics("ecis").feature("bgnd1").selection().set(70);
    model.component("comp1").physics("ecis").create("bterm1", "BoundaryTerminal", 1);
    model.component("comp1").physics("ecis").feature("bterm1").selection().set(11, 13);
    model.component("comp1").physics("ecis").feature("bterm1").set("TerminalType", "Voltage");
    model.component("comp1").physics("ecis").feature("bterm1").set("V0", 3);
    model.component("comp1").physics("ecis").create("bterm2", "BoundaryTerminal", 1);
    model.component("comp1").physics("ecis").feature("bterm2").selection().set(7);
    model.component("comp1").physics("ecis").create("bterm3", "BoundaryTerminal", 1);
    model.component("comp1").physics("ecis").feature("bterm3").selection().set(72, 73);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 60);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.5);
    model.component("comp1").mesh("mesh1").feature().duplicate("size1", "size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 2);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", 0.1);
    model.component("comp1").mesh("mesh1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", 6);
    model.component("comp1").mesh("mesh1").feature().duplicate("size3", "size2");
    model.component("comp1").mesh("mesh1").feature("size3").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size3").selection()
         .set(27, 29, 38, 39, 40, 41, 42, 43, 44, 51, 52, 53);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmax", 0.4);
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 2);
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 3);
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 4);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1shellshl", "Shell");
    model.result().dataset("dset1shellshl").set("data", "dset1");
    model.result().dataset("dset1shellshl").setIndex("topconst", "1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("bottomconst", "-1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset1shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset1shellshl").set("seplevels", false);
    model.result().dataset("dset1shellshl").set("resolution", 2);
    model.result().dataset("dset1shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset1shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1shellshl");
    model.result("pg1").label("\u5e94\u529b (shell)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (ecis)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "V");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("def1", "Deform");
    model.result("pg2").feature("surf1").feature("def1")
         .set("expr", new String[]{"0.5*ecis.ds*ecis.nX", "0.5*ecis.ds*ecis.nY", "0.5*ecis.ds*ecis.nZ"});
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("showsolutionparams", "on");
    model.result("pg2").feature("surf2").set("solutionparams", "parent");
    model.result("pg2").feature("surf2").set("expr", "V");
    model.result("pg2").feature("surf2").set("titletype", "none");
    model.result("pg2").feature("surf2").set("showsolutionparams", "on");
    model.result("pg2").feature("surf2").set("data", "parent");
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").feature("surf2").feature().create("def1", "Deform");
    model.result("pg2").feature("surf2").feature("def1")
         .set("expr", new String[]{"-0.5*ecis.ds*ecis.nX", "-0.5*ecis.ds*ecis.nY", "-0.5*ecis.ds*ecis.nZ"});
    model.result("pg1").run();
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("box1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "shell.disp");
    model.result("pg3").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u9762\u5185\u526a\u5207\u5e94\u529b\uff08\u5c40\u90e8\u5750\u6807\uff09");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "shell.SlGp12");
    model.result("pg4").feature("surf1")
         .set("descr", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c12 \u5206\u91cf");
    model.result("pg4").run();
    model.result("pg4").create("mms1", "MaxMinSurface");
    model.result("pg4").feature("mms1").set("expr", "shell.SlGp12");
    model.result("pg4").feature("mms1")
         .set("descr", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c12 \u5206\u91cf");
    model.result("pg4").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u9762\u5185\u526a\u5207\u5e94\u529b\uff08\u5c40\u90e8\u5750\u6807\u7cfb\uff09");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(6, 76);
    model.result("pg5").feature("lngr1").set("expr", "shell.SlGp12");
    model.result("pg5").feature("lngr1")
         .set("descr", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c12 \u5206\u91cf");
    model.result("pg5").run();
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").selection().named("sel1");
    model.result().dataset("dset3").selection().named("uni2");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u6d41\u548c\u7535\u538b");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").create("con1", "Contour");
    model.result("pg6").feature("con1").set("expr", "V");
    model.result("pg6").feature("con1").set("number", 40);
    model.result("pg6").feature("con1").set("colortable", "ThermalDark");
    model.result("pg6").feature("con1").set("colortabletrans", "reverse");
    model.result("pg6").run();
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"ecis.JsX", "ecis.JsY", "ecis.JsZ"});
    model.result("pg6").feature("arws1")
         .set("descr", "\u8868\u9762\u7535\u6d41\u5bc6\u5ea6 \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg6").feature("arws1").set("descractive", true);
    model.result("pg6").feature("arws1").set("descr", "\u8868\u9762\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg6").feature("arws1").set("scaleactive", true);
    model.result("pg6").feature("arws1").set("scale", 0.005);
    model.result("pg6").feature("arws1").set("arrowcount", 3000);
    model.result("pg6").feature("arws1").set("color", "blue");
    model.result("pg6").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"ecis.I0_1"});
    model.result().numerical("gev1").set("descr", new String[]{"\u7ec8\u7aef\u7535\u6d41"});
    model.result().numerical("gev1").set("unit", new String[]{"A"});
    model.result().numerical("gev1").set("expr", new String[]{"ecis.I0_1", "ecis.V0_2"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u7ec8\u7aef\u7535\u6d41", "\u7ec8\u7aef\u7535\u538b"});
    model.result().numerical("gev1").setIndex("unit", "mA", 0);
    model.result().numerical("gev1").setIndex("expr", "ecis.V0_2-ecis.V0_3", 1);
    model.result().numerical("gev1").setIndex("unit", "mV", 1);
    model.result().numerical("gev1").setIndex("descr", "\u5668\u4ef6\u8f93\u51fa", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg6").run();

    model.title("\u538b\u963b\u5f0f\u538b\u529b\u4f20\u611f\u5668\uff0c\u58f3");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u538b\u963b\u5f0f\u538b\u529b\u4f20\u611f\u5668\uff0c\u6f14\u793a\u4e86\u5f53\u56db\u7aef\u538b\u654f\u7535\u963b\u5668\u6240\u5d4c\u5165\u7684\u819c\u53d7\u5916\u52a0\u538b\u529b\u4f5c\u7528\u800c\u4ea7\u751f\u53d8\u5f62\u65f6\uff0c\u5982\u4f55\u8ba1\u7b97\u5176\u4e2d\u4ea7\u751f\u7684\u5e94\u529b\u8bf1\u5bfc\u7535\u4f4d\u5dee\u3002\u6a21\u578b\u4e2d\u91c7\u7528\u201c\u538b\u963b\uff0c\u58f3\u201d\u63a5\u53e3\uff0c\u57fa\u4e8e\u5bf9\u65e9\u671f MEMS \u538b\u529b\u4f20\u611f\u5668\u7684\u5206\u6790\uff1a\u6469\u6258\u7f57\u62c9 MAP \u4f20\u611f\u5668 (S.D. Senturia, Microsystem Design, Springer, 2000)\u3002\n\n\u8be5\u6a21\u578b\u9700\u8981\u201cMEMS \u6a21\u5757\u201d\u548c\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("piezoresistive_pressure_sensor_shell.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
