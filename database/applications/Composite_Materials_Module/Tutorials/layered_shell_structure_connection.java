/*
 * layered_shell_structure_connection.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:35 by COMSOL 6.3.0.290. */
public class layered_shell_structure_connection {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("shell", "Shell", "geom1");
    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std1").feature("stat").setSolveFor("/physics/lshell", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics().move("lshell", 1);

    model.param().set("a", "1[m]");
    model.param().descr("a", "\u8fb9\u957f");
    model.param().set("th", "1e-2[m]");
    model.param().descr("th", "\u5c42\u539a\u5ea6");
    model.param().set("F", "10[kN]");
    model.param().descr("F", "\u603b\u8f7d\u8377");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"0.4*a", "0.5*a"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0.3*a", "-0.5*a"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("rot", "90 180 270");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1")
         .set("pos", new String[]{"0.5*a", "0.5*a"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("spl1", "Split");
    model.component("comp1").geom("geom1").feature("spl1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").run("spl1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("spl1(1)", 1);
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("spl1(3)", 1);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "20*th", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("ext1(2)");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "10*th");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").set("spl1(4)");
    model.component("comp1").geom("geom1").feature("mov2").set("displz", "-2*th");
    model.component("comp1").geom("geom1").run("mov2");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"1.4*a", "a"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"-0.2*a", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("size", new String[]{"0.2*a", "a"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("pos", new String[]{"-0.2*a", "0"});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("mov3", "Move");
    model.component("comp1").geom("geom1").feature("mov3").selection("input")
         .set("ext1(1)", "mov1", "mov2", "spl1(2)", "wp2");
    model.component("comp1").geom("geom1").feature("mov3").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov3").set("disply", "2.5*a");
    model.component("comp1").geom("geom1").run("mov3");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("mov3(4)", 1);
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("mov3(5)", 1, 2);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "th", 0);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "2*th", 1);
    model.component("comp1").geom("geom1").feature("ext2").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").set("mov3(3)", 1);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "th", 0);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "2*th", 1);
    model.component("comp1").geom("geom1").feature("ext3").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 3);
    model.component("comp1").variable("var1").selection().set(4, 6);
    model.component("comp1").variable("var1").set("misesTop_solid", "solid.mises");
    model.component("comp1").variable("var1").descr("misesTop_solid", "von Mises \u5e94\u529b");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 3);
    model.component("comp1").variable("var2").selection().set(3, 5);
    model.component("comp1").variable("var2").set("misesBot_solid", "solid.mises");
    model.component("comp1").variable("var2").descr("misesBot_solid", "von Mises \u5e94\u529b");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").set("misesTop_lshell", "lshell.atxd1(2*th,mean(lshell.mises))");
    model.component("comp1").variable("var3").descr("misesTop_lshell", "von Mises \u5e94\u529b");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.material("mat1").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.material("mat1").propertyGroup().create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.material("mat1").label("Structural steel");
    model.material("mat1").set("family", "custom");
    model.material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat1").set("diffuse", "custom");
    model.material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("ambient", "custom");
    model.material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("noise", true);
    model.material("mat1").set("fresnel", 0.9);
    model.material("mat1").set("roughness", 0.3);
    model.material("mat1").set("diffusewrap", 0);
    model.material("mat1").set("reflectance", 0);
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.material("mat1").propertyGroup("Enu").addInput("temperature");
    model.material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.material("mat1").propertyGroup("ElastoplasticModel").label("Elastoplastic material model");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.material("mat1").propertyGroup("Voce").addInput("temperature");
    model.material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.material("mat1").propertyGroup("Norton").label("Norton");
    model.material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").label("Chaboche viscoplasticity");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup()
         .create("OrthotropicStrengthParameters", "OrthotropicStrengthParameters", "Orthotropic strength parameters, Voigt notation");
    model.material("mat2").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.material("mat2")
         .label("Unidirectional fiber lamina: AS4/APC2 carbon/PEEK thermoplastic [fiber volume fraction 58%]");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def").set("density", "1570[kg/m^3]");
    model.material("mat2").propertyGroup("def")
         .setPropertyInfo("density", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.2E-6[1/K]", "0", "0", "0", "24E-6[1/K]", "0", "0", "0", "24E-6[1/K]"});
    model.material("mat2").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("OrthotropicStrengthParameters")
         .label("Orthotropic strength parameters, Voigt notation");
    model.material("mat2").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmats", new String[]{"2060[MPa]", "78[MPa]", "78[MPa]"});
    model.material("mat2").propertyGroup("OrthotropicStrengthParameters")
         .setPropertyInfo("sigmats", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmacs", new String[]{"1590[MPa]", "200[MPa]", "200[MPa]"});
    model.material("mat2").propertyGroup("OrthotropicStrengthParameters")
         .setPropertyInfo("sigmacs", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmass", new String[]{"157[MPa]", "157[MPa]", "157[MPa]"});
    model.material("mat2").propertyGroup("OrthotropicStrengthParameters")
         .setPropertyInfo("sigmass", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("TransverseIsotropic").label("Transversely isotropic");
    model.material("mat2").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"138[GPa]", "8.7[GPa]"});
    model.material("mat2").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.28", "0.45"});
    model.material("mat2").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("TransverseIsotropic").set("Gvect1", "5[GPa]");
    model.material("mat2").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Gvect1", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").setIndex("link", "mat2", 0);
    model.material("lmat1").setIndex("thickness", "th", 0);
    model.material("lmat1").setIndex("meshPoints", 1, 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat2", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat2", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("rotation", 45, 1);
    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");
    model.component("comp1").material("llmat1").selection().set(11, 12, 13, 25, 37, 69);
    model.component("comp1").material("llmat1").set("middlePlane", "bottom");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").selection().set(3, 4, 5, 6, 8, 9, 11, 12);
    model.component("comp1").material("matlnk1").set("link", "mat2");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().set(1, 2, 7, 10);

    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 2);
    model.component("comp1").physics("solid").feature("lemm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("solid").create("lemm2", "LinearElasticModel", 3);
    model.component("comp1").physics("solid").feature("lemm2").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("solid").feature("lemm2").selection().set(4, 6, 9, 12);

    model.component("comp1").coordSystem().create("sys2", "Rotated");
    model.component("comp1").coordSystem("sys2").set("angle", new String[]{"pi/4", "0", "0"});

    model.component("comp1").physics("solid").feature("lemm2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").create("asl1", "AuxiliarySlit", 2);
    model.component("comp1").physics("solid").feature("asl1").selection().set(57);
    model.component("comp1").physics("solid").feature("asl1")
         .comments("This feature disconnect the displacement field on either sides of selected boundaries, thus creating a discontinuity along the selected boundaries.");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(20, 33);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1").set("force", new String[]{"0", "0", "F"});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1, 6, 43, 45, 48, 60, 82, 83);
    model.component("comp1").physics("lshell").selection().set(11, 12, 13, 25);
    model.component("comp1").physics("lshell").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("lshell").feature("fl1").selection().all();
    model.component("comp1").physics("lshell").feature("fl1").set("applyTo", "top");
    model.component("comp1").physics("lshell").feature("fl1").set("forceType", "TotalForce");
    model.component("comp1").physics("lshell").feature("fl1").set("force", new String[]{"0", "0", "F"});
    model.component("comp1").physics("shell").selection().set(37, 69);
    model.component("comp1").physics("shell").create("llem1", "LayeredElastic", 2);
    model.component("comp1").physics("shell").feature("llem1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("shell").feature("llem1").selection().set(37, 69);
    model.component("comp1").physics("shell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("shell").feature("fix1").selection().set(68, 163);

    model.component("comp1").multiphysics().create("lssc1", "LayeredShellStructCladding", -1);
    model.component("comp1").multiphysics("lssc1").set("LayeredShellBnd", "Bottom");
    model.component("comp1").multiphysics().create("lsst1", "LayeredShellStructTransition", 1);
    model.component("comp1").multiphysics("lsst1").set("allLayers", false);
    model.component("comp1").multiphysics("lsst1").setIndex("shelllist_lCheck", 0, 1, 0);
    model.component("comp1").multiphysics("lsst1").selection().set(74);
    model.component("comp1").multiphysics("lsst1").set("selectionControl", true);
    model.component("comp1").multiphysics("lsst1").selection("edgBndSolidSelection").set(39, 41);
    model.component("comp1").multiphysics().create("lssc2", "LayeredShellStructCladding", -1);
    model.component("comp1").multiphysics("lssc2").set("Shell_physics", "shell");
    model.component("comp1").multiphysics("lssc2").set("connectionSettings", "parallelBnd");
    model.component("comp1").multiphysics("lssc2").selection("paraBndLShellSelection").set(25);
    model.component("comp1").multiphysics("lssc2").selection("paraBndShellSelection").set(69);
    model.component("comp1").multiphysics("lssc2").set("LayeredShellBnd", "Bottom");
    model.component("comp1").multiphysics("lssc2").set("ShellBndParallel", "Top");
    model.component("comp1").multiphysics().create("lsst2", "LayeredShellStructTransition", 1);
    model.component("comp1").multiphysics("lsst2").set("Shell_physics", "shell");
    model.component("comp1").multiphysics("lsst2").selection().set(69);

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.03);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 9.0E-4);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.3);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.2);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 1);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(11, 13, 25, 37, 69);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset().create("lshl2", "LayeredMaterial");
    model.result().dataset("lshl2").set("usealllayers", false);
    model.result().dataset("lshl2").setIndex("layerselection", false, 1, 0);
    model.result().dataset("lshl2").label("\u591a\u5c42\u6750\u6599\uff1a\u5e95\u5c42");
    model.result().dataset().create("lshl3", "LayeredMaterial");
    model.result().dataset("lshl3").label("\u591a\u5c42\u6750\u6599\uff1a\u9876\u5c42");

    return model;
  }

  public static Model run2(Model model) {
    model.result().dataset("lshl3").set("usealllayers", false);
    model.result().dataset("lshl3").setIndex("layerselection", false, 0, 0);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "solid.mises");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormax", 10);
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("data", "lshl1");
    model.result("pg1").feature("surf2").set("expr", "lshell.mises");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("surf2").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("def1").set("expr", new String[]{"u3", "v3", "w3"});
    model.result("pg1").run();
    model.result("pg1").create("surf3", "Surface");
    model.result("pg1").feature("surf3").set("data", "lshl1");
    model.result("pg1").feature("surf3").set("expr", "shell.mises");
    model.result("pg1").feature("surf3").set("titletype", "none");
    model.result("pg1").feature("surf3").set("inheritplot", "surf1");
    model.result("pg1").feature("surf3").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("def1").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 1.5, 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 1.5, 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg1").feature("tlan1")
         .setIndex("localtablematrix", "\u591a\u5c42\u58f3-\u5b9e\u4f53-\u58f3", 0, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 1.5, 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 4, 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u5b9e\u4f53\uff08\u53c2\u8003\uff09", 1, 3);
    model.result("pg1").feature("tlan1").set("showpoint", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u4f4d\u79fb");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "solid.disp");
    model.result("pg2").feature("surf1").set("rangecoloractive", false);
    model.result("pg2").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("expr", "lshell.disp");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").set("expr", "shell.disp");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\uff1a\u591a\u5c42\u58f3\uff0c\u5e95\u5c42");
    model.result("pg3").run();
    model.result("pg3").feature().remove("surf3");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "misesBot_solid");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("data", "lshl2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u529b\uff1a\u591a\u5c42\u58f3\uff0c\u9876\u5c42");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "misesTop_solid");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("data", "lshl3");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5e94\u529b\uff0c\u591a\u5c42\u58f3-\u5b9e\u4f53\u5305\u5c42");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "Y \u5750\u6807 (m)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "von Mises \u5e94\u529b (MPa)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(17, 19, 21);
    model.result("pg5").feature("lngr1").set("expr", "misesTop_lshell");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "\u591a\u5c42\u58f3", 0);
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").selection().set(30);
    model.result("pg5").feature("lngr2").set("expr", "solid.mises");
    model.result("pg5").feature("lngr2").set("linestyle", "dashed");
    model.result("pg5").feature("lngr2").setIndex("legends", "\u5b9e\u4f53\uff08\u53c2\u8003\uff09", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u5e94\u529b\uff0c\u591a\u5c42\u58f3-\u58f3\u5305\u5c42");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").selection().set(151);
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").selection().set(156);
    model.result("pg6").feature("lngr2").set("xdata", "reversedarc");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u5e94\u529b\uff0c\u591a\u5c42\u58f3-\u58f3\u8fc7\u6e21");
    model.result("pg7").set("xlabel", "X \u5750\u6807 (m)");
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").selection().set(18, 42, 69, 108);
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").selection().set(31, 56, 92, 124);
    model.result("pg7").feature("lngr2").set("xdata", "arc");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u5e94\u529b\uff0c\u591a\u5c42\u58f3-\u5b9e\u4f53\u8fc7\u6e21");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").selection().set(23, 48, 74, 112);
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("expr", "misesTop_solid");
    model.result("pg8").feature("lngr2").selection().set(40, 66, 101, 132);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg3").run();

    model.title("\u591a\u5c42\u58f3\u4e0e\u5b9e\u4f53\u548c\u58f3\u7684\u8fde\u63a5");

    model
         .description("\u7528\u4e8e\u590d\u5408\u58f3\u5efa\u6a21\u7684\u591a\u5c42\u58f3\u5355\u5143\u901a\u5e38\u5728\u5305\u5c42\u6216\u5e76\u6392\u6784\u578b\u4e2d\u4e0e\u5b9e\u4f53\u548c\u58f3\u5355\u5143\u8fde\u63a5\uff0c\u4ee5\u8868\u793a\u771f\u5b9e\u7684\u7ed3\u6784\u3002\u5bf9\u4e8e\u8fd9\u7c7b\u5e94\u7528\uff0c\u6b63\u786e\u4e14\u65b9\u4fbf\u5730\u5c06\u591a\u5c42\u58f3\u5355\u5143\u4e0e\u5176\u4ed6\u7ed3\u6784\u5355\u5143\u8fde\u63a5\u663e\u5f97\u5c24\u4e3a\u5173\u952e\u3002\n\n\u5728\u8fd9\u4e2a\u6559\u7a0b\u548c\u9a8c\u8bc1\u95ee\u9898\u4e2d\uff0c\u60a8\u5c06\u5b66\u4e60\u5982\u4f55\u5728\u4e0d\u540c\u7684\u6784\u578b\u4e2d\u5c06\u591a\u5c42\u58f3\u5355\u5143\u8fde\u63a5\u5230\u5b9e\u4f53\u548c\u58f3\u5355\u5143\uff0c\u5176\u4e2d\u8fd8\u5c06\u6240\u5f97\u7ed3\u679c\u4e0e\u4f7f\u7528\u5b9e\u4f53\u5355\u5143\u6784\u5efa\u7684\u53c2\u8003\u6a21\u578b\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("layered_shell_structure_connection.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
