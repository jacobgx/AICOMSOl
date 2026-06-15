/*
 * pratt_truss_bridge.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:00 by COMSOL 6.3.0.290. */
public class pratt_truss_bridge {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Beams_and_Shells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");
    model.component("comp1").physics().create("beam", "HermitianBeam", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std1").feature("stat").setSolveFor("/physics/beam", true);

    model.component("comp1").geom("geom1").insertFile("pratt_truss_bridge_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("truck_weight", "12000[kg]");
    model.param().descr("truck_weight", "\u5361\u8f66\u603b\u91cd");
    model.param().set("Fz", "-truck_weight*g_const/6");
    model.param().descr("Fz", "\u70b9\u8f7d\u8377");
    model.param().set("para", "0");
    model.param().descr("para", "\u53c2\u6570");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u6881\uff0c\u5e95\u90e8\u6a2a\u5411");
    model.component("comp1").selection("box1").set("entitydim", 1);
    model.component("comp1").selection("box1").set("xmin", "-(length/2+1)");
    model.component("comp1").selection("box1").set("xmax", "length/2+1");
    model.component("comp1").selection("box1").set("ymin", 1);
    model.component("comp1").selection("box1").set("ymax", "width-1");
    model.component("comp1").selection("box1").set("zmin", -1);
    model.component("comp1").selection("box1").set("zmax", 1);
    model.component("comp1").selection().duplicate("box2", "box1");
    model.component("comp1").selection("box2").label("\u6881\uff0c\u5e95\u90e8\u5168\u90e8");
    model.component("comp1").selection("box2").set("ymin", -1);
    model.component("comp1").selection("box2").set("ymax", "width+1");
    model.component("comp1").selection("box2").set("condition", "inside");
    model.component("comp1").selection().create("box3", "Box");
    model.component("comp1").selection("box3").label("\u6881\uff0c\u9876\u90e8\u6a2a\u5411");
    model.component("comp1").selection("box3").set("entitydim", 1);
    model.component("comp1").selection("box3").set("xmin", "-(length/2+1)");
    model.component("comp1").selection("box3").set("xmax", "length/2+1");
    model.component("comp1").selection("box3").set("ymin", 1);
    model.component("comp1").selection("box3").set("ymax", "width-1");
    model.component("comp1").selection("box3").set("zmin", "height-1");
    model.component("comp1").selection("box3").set("zmax", "height+1");
    model.component("comp1").selection().create("box4", "Box");
    model.component("comp1").selection("box4").label("\u6881\uff0c\u659c\u6784\u4ef6");
    model.component("comp1").selection("box4").set("entitydim", 1);
    model.component("comp1").selection("box4").set("xmin", "-(length/2-spacing+1)");
    model.component("comp1").selection("box4").set("xmax", "length/2-spacing+1");
    model.component("comp1").selection("box4").set("ymin", -1);
    model.component("comp1").selection("box4").set("ymax", "width+1");
    model.component("comp1").selection("box4").set("zmin", 1);
    model.component("comp1").selection("box4").set("zmax", 2);
    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6881\uff0c\u5168\u90e8");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u6881\uff0c\u4e3b\u6784\u4ef6");
    model.component("comp1").selection("dif1").set("entitydim", 1);
    model.component("comp1").selection("dif1").set("add", new String[]{"sel1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"box1", "box3", "box4"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Concrete");
    model.component("comp1").material("mat1").set("family", "concrete");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"10e-6[1/K]", "0", "0", "0", "10e-6[1/K]", "0", "0", "0", "10e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "880[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "25[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.20");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat2").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat2").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat2").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat2").label("Structural steel");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.3);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat2").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material("mat2").selection().geom("geom1", 1);
    model.component("comp1").material("mat2").selection().all();

    model.component("comp1").physics("shell").feature("to1").set("d", 0.25);
    model.component("comp1").physics("shell").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("shell").create("pin1", "Pinned", 1);
    model.component("comp1").physics("shell").feature("pin1").selection().set(1);
    model.component("comp1").physics("shell").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("shell").feature("disp1").selection().set(74);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").create("plf1", "PointLoadFree", -1);
    model.component("comp1").physics("shell").feature("plf1")
         .label("\u70b9\u8f7d\u8377\uff0c\u81ea\u7531 [\u7b2c\u4e00\u8f86\u5361\u8f66]");
    model.component("comp1").physics("shell").feature("plf1").setIndex("Xp", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Zp", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmxl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmyl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmxl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmyl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmzl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Zp", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmxl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmyl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmxl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmyl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmzl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Xp", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Zp", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmxl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmyl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmxl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmyl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmzl", 0, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Xp", "-22+3*para", 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 1, 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", "Fz", 0, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Xp", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Zp", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmxl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmyl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmxl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmyl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmzl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Zp", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmxl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmyl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmxl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmyl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmzl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Xp", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Zp", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmxl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmyl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmxl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmyl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmzl", 0, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Xp", "-22+3*para", 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 3, 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", "Fz", 1, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Xp", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Zp", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmxl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmyl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmxl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmyl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmzl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Zp", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmxl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmyl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmxl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmyl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmzl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Xp", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Zp", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmxl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmyl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmxl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmyl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmzl", 0, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Xp", "-28+3*para", 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 1, 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", "2*Fz", 2, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Xp", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Zp", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmxl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmyl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmxl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmyl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmzl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Zp", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmxl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmyl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmxl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmyl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmzl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Xp", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Zp", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmxl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmyl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmxl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmyl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Mmzl", 0, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Xp", "-28+3*para", 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Yp", 3, 3, 0);
    model.component("comp1").physics("shell").feature("plf1").setIndex("Fmzl", "2*Fz", 3, 0);
    model.component("comp1").physics("shell").feature().duplicate("plf2", "plf1");
    model.component("comp1").physics("shell").feature("plf2")
         .label("\u70b9\u8f7d\u8377\uff0c\u81ea\u7531 [\u7b2c\u4e8c\u8f86\u5361\u8f66]");
    model.component("comp1").physics("shell").feature("plf2").setIndex("Xp", "-40+3*para", 0, 0);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("shell").feature("plf2").setIndex("Xp", "-40+3*para", 1, 0);
    model.component("comp1").physics("shell").feature("plf2").setIndex("Xp", "-46+3*para", 2, 0);
    model.component("comp1").physics("shell").feature("plf2").setIndex("Xp", "-46+3*para", 3, 0);
    model.component("comp1").physics("beam").feature("csd1").label("\u6a2a\u622a\u9762\uff0c\u4e3b\u6784\u4ef6");
    model.component("comp1").physics("beam").feature("csd1").set("SectionType", "BoxSection");
    model.component("comp1").physics("beam").feature("csd1").set("hy_box", "200[mm]");
    model.component("comp1").physics("beam").feature("csd1").set("hz_box", "200[mm]");
    model.component("comp1").physics("beam").feature("csd1").set("ty_box", "16[mm]");
    model.component("comp1").physics("beam").feature("csd1").set("tz_box", "16[mm]");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("vector_beam", new int[]{0, 1, 0});
    model.component("comp1").physics("beam").create("csd2", "CrossSectionBeam", 1);
    model.component("comp1").physics("beam").feature("csd2").label("\u6a2a\u622a\u9762\uff0c\u659c\u6784\u4ef6");
    model.component("comp1").physics("beam").feature("csd2").selection().named("box4");
    model.component("comp1").physics("beam").feature("csd2").set("SectionType", "BoxSection");
    model.component("comp1").physics("beam").feature("csd2").set("hy_box", "200[mm]");
    model.component("comp1").physics("beam").feature("csd2").set("hz_box", "100[mm]");
    model.component("comp1").physics("beam").feature("csd2").set("ty_box", "12.5[mm]");
    model.component("comp1").physics("beam").feature("csd2").set("tz_box", "12.5[mm]");
    model.component("comp1").physics("beam").feature("csd2").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd2").feature("so1").set("vector_beam", new int[]{0, 1, 0});
    model.component("comp1").physics("beam").create("csd3", "CrossSectionBeam", 1);
    model.component("comp1").physics("beam").feature("csd3")
         .label("\u6a2a\u622a\u9762\uff0c\u5e95\u90e8\u6a2a\u5411");
    model.component("comp1").physics("beam").feature("csd3").selection().named("box1");
    model.component("comp1").physics("beam").feature("csd3").set("SectionType", "H_Profile");
    model.component("comp1").physics("beam").feature("csd3").set("hy_H", "96[mm]");
    model.component("comp1").physics("beam").feature("csd3").set("hz_H", "100[mm]");
    model.component("comp1").physics("beam").feature("csd3").set("ty_H", "8[mm]");
    model.component("comp1").physics("beam").feature("csd3").set("tz_H", "5[mm]");
    model.component("comp1").physics("beam").feature("csd3").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd3").feature("so1").set("vector_beam", new int[]{0, 0, 1});
    model.component("comp1").physics("beam").create("csd4", "CrossSectionBeam", 1);
    model.component("comp1").physics("beam").feature("csd4")
         .label("\u6a2a\u622a\u9762\uff0c\u9876\u90e8\u6a2a\u5411");
    model.component("comp1").physics("beam").feature("csd4").selection().named("box3");
    model.component("comp1").physics("beam").feature("csd4").set("SectionType", "RectangularSection");
    model.component("comp1").physics("beam").feature("csd4").set("hy_rect", "100[mm]");
    model.component("comp1").physics("beam").feature("csd4").set("hz_rect", "25[mm]");
    model.component("comp1").physics("beam").feature("csd4").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd4").feature("so1").set("vector_beam", new int[]{1, 0, 0});
    model.component("comp1").physics("beam").create("gacc1", "GravityAcceleration", -1);

    model.component("comp1").multiphysics().create("shbc1", "ShellBeamConnection", -1);
    model.component("comp1").multiphysics("shbc1").set("connectionSettings", "BeamSharedEdge");
    model.component("comp1").multiphysics("shbc1").set("selectionControl", true);
    model.component("comp1").multiphysics("shbc1").selection("sharedEdgShellSelection")
         .set(2, 8, 18, 28, 38, 48, 58, 68);
    model.component("comp1").multiphysics("shbc1").set("offset", "OffsetVect");
    model.component("comp1").multiphysics("shbc1").set("d0", new String[]{"0", "beam.hy_box/2", "-beam.hz_box/2"});
    model.component("comp1").multiphysics().duplicate("shbc2", "shbc1");
    model.component("comp1").multiphysics("shbc2").selection("sharedEdgShellSelection")
         .set(4, 13, 23, 33, 43, 53, 63, 72);
    model.component("comp1").multiphysics("shbc2").set("d0", new String[]{"0", "-beam.hy_box/2", "-beam.hz_box/2"});
    model.component("comp1").multiphysics().create("shbc3", "ShellBeamConnection", -1);
    model.component("comp1").multiphysics("shbc3").set("connectionSettings", "BeamSharedEdge");
    model.component("comp1").multiphysics("shbc3").set("selectionControl", true);
    model.component("comp1").multiphysics("shbc3").selection("sharedEdgShellSelection").named("box1");
    model.component("comp1").multiphysics("shbc3").set("offset", "OffsetVect");
    model.component("comp1").multiphysics("shbc3").set("d0", new String[]{"0", "0", "-beam.hy_H/2"});

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "width", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "width", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,1,15)", 0);
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
    model.result("pg1").setIndex("looplevel", 16, 0);
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
    model.result().dataset().create("dset1beam", "Beam");
    model.result().dataset("dset1beam").set("data", "dset1");
    model.result().dataset("dset1beam").set("physicsinterface", "beam");
    model.result().dataset("dset1beam").set("refinement", 2);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1beam");
    model.result("pg2").setIndex("looplevel", 16, 0);
    model.result("pg2").label("\u5e94\u529b\uff0c\u4e09\u7ef4 (beam)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"beam.misesS"});
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"beam.uS", "beam.vS", "beam.wS"});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "MPa");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("unit", "mm");
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormax", 50);
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("data", "dset1beam");
    model.result("pg3").feature("surf2").set("solutionparams", "parent");
    model.result("pg3").feature("surf2").set("expr", "beam.disp");
    model.result("pg3").feature("surf2").set("unit", "mm");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").feature("surf2").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("def1").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg3").run();
    model.result("pg3").feature("surf2").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("pttraj1", "PointTrajectories");
    model.result("pg3").feature("pttraj1").set("expr", new String[]{"shell.plf1.Xp1", "0", "0"});
    model.result("pg3").feature("pttraj1").setIndex("expr", "shell.plf1.Yp1", 1);
    model.result("pg3").feature("pttraj1").set("linetype", "none");
    model.result("pg3").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg3").feature("pttraj1").set("arrowexpr", new String[]{"0", "0", "Fz"});
    model.result("pg3").feature("pttraj1").set("arrowbase", "head");
    model.result("pg3").feature("pttraj1").set("arrowscaleactive", true);
    model.result("pg3").feature("pttraj1").set("arrowscale", "12E-5");
    model.result("pg3").feature("pttraj1").set("inheritplot", "surf1");
    model.result("pg3").feature("pttraj1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj1").feature("def1").set("expr", new String[]{"shell.plf1.u_1", "0", "0"});
    model.result("pg3").feature("pttraj1").feature("def1").setIndex("expr", "shell.plf1.v_1", 1);
    model.result("pg3").feature("pttraj1").feature("def1").setIndex("expr", "shell.plf1.w_1", 2);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("pttraj2", "pttraj1");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj2").setIndex("expr", "shell.plf1.Xp2", 0);
    model.result("pg3").feature("pttraj2").setIndex("expr", "shell.plf1.Yp2", 1);
    model.result("pg3").feature("pttraj2").set("inheritplot", "pttraj1");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj2").feature("def1").setIndex("expr", "shell.plf1.u_2", 0);
    model.result("pg3").feature("pttraj2").feature("def1").setIndex("expr", "shell.plf1.v_2", 1);
    model.result("pg3").feature("pttraj2").feature("def1").setIndex("expr", "shell.plf1.w_2", 2);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("pttraj3", "pttraj2");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj3").setIndex("expr", "shell.plf1.Xp3", 0);
    model.result("pg3").feature("pttraj3").setIndex("expr", "shell.plf1.Yp3", 1);
    model.result("pg3").feature("pttraj3").set("arrowexpr", new String[]{"0", "0", "2*Fz"});
    model.result("pg3").run();
    model.result("pg3").feature("pttraj3").feature("def1").setIndex("expr", "shell.plf1.u_3", 0);
    model.result("pg3").feature("pttraj3").feature("def1").setIndex("expr", "shell.plf1.v_3", 1);
    model.result("pg3").feature("pttraj3").feature("def1").setIndex("expr", "shell.plf1.w_3", 2);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("pttraj4", "pttraj3");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj4").setIndex("expr", "shell.plf1.Xp4", 0);
    model.result("pg3").feature("pttraj4").setIndex("expr", "shell.plf1.Yp4", 1);
    model.result("pg3").run();
    model.result("pg3").feature("pttraj4").feature("def1").setIndex("expr", "shell.plf1.u_4", 0);
    model.result("pg3").feature("pttraj4").feature("def1").setIndex("expr", "shell.plf1.v_4", 1);
    model.result("pg3").feature("pttraj4").feature("def1").setIndex("expr", "shell.plf1.w_4", 2);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("pttraj5", "pttraj1");
    model.result("pg3").feature().duplicate("pttraj6", "pttraj2");
    model.result("pg3").feature().duplicate("pttraj7", "pttraj3");
    model.result("pg3").feature().duplicate("pttraj8", "pttraj4");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj5").setIndex("expr", "shell.plf2.Xp1", 0);
    model.result("pg3").feature("pttraj5").setIndex("expr", "shell.plf2.Yp1", 1);
    model.result("pg3").feature("pttraj5").set("pointcolor", "magenta");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj5").feature("def1").setIndex("expr", "shell.plf2.u_1", 0);
    model.result("pg3").feature("pttraj5").feature("def1").setIndex("expr", "shell.plf2.v_1", 1);
    model.result("pg3").feature("pttraj5").feature("def1").setIndex("expr", "shell.plf2.w_1", 2);
    model.result("pg3").run();
    model.result("pg3").feature("pttraj6").setIndex("expr", "shell.plf2.Xp2", 0);
    model.result("pg3").feature("pttraj6").setIndex("expr", "shell.plf2.Yp2", 1);
    model.result("pg3").feature("pttraj6").set("inheritplot", "pttraj5");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj6").feature("def1").setIndex("expr", "shell.plf2.u_2", 0);
    model.result("pg3").feature("pttraj6").feature("def1").setIndex("expr", "shell.plf2.v_2", 1);
    model.result("pg3").feature("pttraj6").feature("def1").setIndex("expr", "shell.plf2.w_2", 2);
    model.result("pg3").run();
    model.result("pg3").feature("pttraj7").set("inheritplot", "pttraj5");
    model.result("pg3").feature("pttraj7").setIndex("expr", "shell.plf2.Xp3", 0);
    model.result("pg3").feature("pttraj7").setIndex("expr", "shell.plf2.Yp3", 1);
    model.result("pg3").run();
    model.result("pg3").feature("pttraj7").feature("def1").setIndex("expr", "shell.plf2.u_3", 0);
    model.result("pg3").feature("pttraj7").feature("def1").setIndex("expr", "shell.plf2.v_3", 1);
    model.result("pg3").feature("pttraj7").feature("def1").setIndex("expr", "shell.plf2.w_3", 2);
    model.result("pg3").run();
    model.result("pg3").feature("pttraj8").setIndex("expr", "shell.plf2.Xp4", 0);
    model.result("pg3").feature("pttraj8").setIndex("expr", "shell.plf2.Yp4", 1);
    model.result("pg3").feature("pttraj8").set("inheritplot", "pttraj5");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj8").feature("def1").setIndex("expr", "shell.plf2.u_4", 0);
    model.result("pg3").feature("pttraj8").feature("def1").setIndex("expr", "shell.plf2.v_4", 1);
    model.result("pg3").feature("pttraj8").feature("def1").setIndex("expr", "shell.plf2.w_4", 2);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").set("maxframes", 9);
    model.result().export("anim1").set("showframe", 9);
    model.result().export("anim1").set("frametime", 0.5);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 16, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"beam.Nxl"});
    model.result("pg4").feature("line1").set("threshold", "manual");
    model.result("pg4").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("line1").set("colortable", "Wave");
    model.result("pg4").feature("line1").set("colortabletrans", "none");
    model.result("pg4").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("radiusexpr", "beam.re");
    model.result("pg4").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg4").feature("line1").set("tuberadiusscale", 1);
    model.result("pg4").feature("line1").set("tubeendcaps", false);
    model.result("pg4").feature("line1").create("def", "Deform");
    model.result("pg4").feature("line1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg4").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").label("\u8f74\u5411\u529b (beam)");
    model.result("pg4").label("\u8f74\u5411\u529b (beam)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").label("\u7a33\u6001\u7ed3\u679c");

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std2").feature("eig").setSolveFor("/physics/beam", true);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/shbc1", true);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/shbc2", true);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/shbc3", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 12);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("dset2shellshl", "Shell");
    model.result().dataset("dset2shellshl").set("data", "dset2");
    model.result().dataset("dset2shellshl").setIndex("topconst", "1", 3, 1);
    model.result().dataset("dset2shellshl").setIndex("bottomconst", "-1", 3, 1);
    model.result().dataset("dset2shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset2shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset2shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset2shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset2shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset2shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset2shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset2shellshl").set("seplevels", false);
    model.result().dataset("dset2shellshl").set("resolution", 2);
    model.result().dataset("dset2shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset2shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2shellshl");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").label("\u632f\u578b (shell)");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").set("data", "dset2shellshl");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std2EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 2)");
    model.result().evaluationGroup("std2EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std2EvgFrq").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").set("showlegends", false);
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("expr", new String[]{"beam.disp"});
    model.result("pg6").feature("line1").set("threshold", "manual");
    model.result("pg6").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("line1").set("colortable", "Rainbow");
    model.result("pg6").feature("line1").set("colortabletrans", "none");
    model.result("pg6").feature("line1").set("colorscalemode", "linear");
    model.result("pg6").label("\u632f\u578b (beam)");
    model.result("pg6").feature("line1").set("colortable", "AuroraBorealis");
    model.result("pg6").feature("line1").set("linetype", "tube");
    model.result("pg6").feature("line1").set("radiusexpr", "beam.re");
    model.result("pg6").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg6").feature("line1").set("tuberadiusscale", 1);
    model.result("pg6").feature("line1").set("tubeendcaps", false);
    model.result("pg6").feature("line1").create("def", "Deform");
    model.result("pg6").feature("line1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg6").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std2mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std2mpf1").set("data", "dset2");
    model.result().evaluationGroup("std2mpf1").label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 2)");
    model.result().evaluationGroup("std2mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);

    return model;
  }

  public static Model run3(Model model) {
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std2mpf1").run();
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{10});
    model.result("pg5").set("edges", false);
    model.result("pg6").run();
    model.result("pg5").run();
    model.result("pg5").feature().copy("line1", "pg6/line1");
    model.result("pg5").run();
    model.result("pg5").feature("line1").set("data", "dset2");
    model.result("pg5").feature("line1").set("solutionparams", "parent");
    model.result("pg5").feature("line1").set("inheritplot", "surf1");
    model.result("pg5").feature("line1").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").label("\u7279\u5f81\u9891\u7387\u7ed3\u679c");

    model.result().dataset().duplicate("dset3", "dset1");
    model.result().dataset("dset3").selection().geom("geom1", 1);
    model.result().dataset("dset3").selection().named("dif1");
    model.result().export().create("data1", "dset3", "Data");
    model.result().export("data1").setIndex("looplevelinput", "manual", 0);
    model.result().export("data1").setIndex("looplevel", new int[]{1}, 0);
    model.result().export("data1").setIndex("expr", "dom", 0);
    model.result().export("data1").setIndex("expr", "beam.Nxl", 1);
    model.result().export("data1").setIndex("unit", "kN", 1);
    model.result().export("data1").setIndex("expr", "beam.Myl", 2);
    model.result().export("data1").setIndex("unit", "kN*m", 2);
    model.result().export("data1").setIndex("expr", "beam.Tzl", 3);
    model.result().export("data1").setIndex("unit", "kN", 3);
    model.result().export("data1").setIndex("expr", "beam.Mzl", 4);
    model.result().export("data1").setIndex("unit", "kN*m", 4);
    model.result().export("data1").setIndex("expr", "beam.Tyl", 5);
    model.result().export("data1").setIndex("unit", "kN", 5);
    model.result().export("data1").setIndex("expr", "beam.Mxl", 6);
    model.result().export("data1").setIndex("unit", "kN*m", 6);
    model.result().export("data1").setIndex("expr", "beam.mises", 7);
    model.result().export("data1").setIndex("unit", "MPa", 7);
    model.result().export("data1").set("level", "line");
    model.result().export("data1").set("fullprec", false);
    model.result("pg5").run();

    model.title("\u666e\u62c9\u7279\u6841\u67b6\u6865");

    model
         .description("\u91c7\u7528\u4e24\u79cd\u4e0d\u540c\u7684\u5206\u6790\u7c7b\u578b\u8ba1\u7b97\u666e\u62c9\u7279\u6841\u67b6\u6865\u7684\u5c5e\u6027\uff0c\u5206\u522b\u5305\u62ec\uff1a\u91cd\u529b\u8f7d\u8377\u548c\u5361\u8f66\u505c\u5728\u6865\u4e0a\u4ea7\u751f\u8f7d\u8377\u5171\u540c\u4f5c\u7528\u4e0b\u7684\u9759\u6001\u5e94\u529b\u548c\u53d8\u5f62\u5206\u6790\uff0c\u4ee5\u53ca\u7279\u5f81\u9891\u7387\u5206\u6790\u3002\u6b64\u6a21\u578b\u7ed3\u5408\u4e86\u4e09\u7ef4\u6b27\u62c9\u6881\u5355\u5143\uff08\u6846\u67b6\u7ed3\u6784\uff09\u548c\u58f3\u5355\u5143\uff08\u9053\u8def\uff09\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("pratt_truss_bridge.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
