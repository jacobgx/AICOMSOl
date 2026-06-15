/*
 * diagonal_brace_buckling_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:39 by COMSOL 6.3.0.290. */
public class diagonal_brace_buckling_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Design_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std1").create("buckling", "LinearBuckling");
    model.study("std1").feature("buckling").set("neigsactive", true);
    model.study("std1").feature("buckling").set("solnum", "auto");
    model.study("std1").feature("buckling").set("notsolnum", "auto");
    model.study("std1").feature("buckling").set("outputmap", new String[]{});
    model.study("std1").feature("buckling").set("ngenAUX", "1");
    model.study("std1").feature("buckling").set("goalngenAUX", "1");
    model.study("std1").feature("buckling").set("ngenAUX", "1");
    model.study("std1").feature("buckling").set("goalngenAUX", "1");
    model.study("std1").feature("buckling").setSolveFor("/physics/shell", true);

    model.param().set("Lz", "6[m]");
    model.param().descr("Lz", "\u652f\u67b6\u957f\u5ea6");
    model.param().set("Lxy", "5[cm]");
    model.param().descr("Lxy", "\u652f\u67b6\u5bbd\u5ea6");
    model.param().set("thickness", "5[mm]");
    model.param().descr("thickness", "\u652f\u67b6\u539a\u5ea6");
    model.param().set("Lz1", "3*Lxy");
    model.param().descr("Lz1", "\u652f\u67b6\u7aef\u957f\u5ea6");
    model.param().set("Rhole", "Lz1/10");
    model.param().descr("Rhole", "\u5b54\u534a\u5f84");
    model.param().set("Fload", "1[kN]");
    model.param().descr("Fload", "\u8f7d\u8377");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Lxy", "Lxy", "Lz"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "Lz1", 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layername", "\u5c42 1", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "Lz-2*Lz1", 1);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u8981\u5220\u9664\u7684\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", "Lxy*0.1");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "Lxy*0.1");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("boxsel1");
    model.component("comp1").geom("geom1").feature("del1").set("selresult", true);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "Rhole");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "Rhole");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"Lxy/2", "-Rhole/2", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("pos", "Lz1/2", 2);
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("pos", "Lz-Lz1/2", 2);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("del1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl1", "cyl2");

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u5185\u90e8\u8fb9\u7f18");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "Lz1*0.999");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "Lz1*1.001");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("cylsel1").set("r", "Rhole*1.01");
    model.component("comp1").geom("geom1").feature("cylsel1").set("pos", new String[]{"Lxy/2", "0", "Lz1/2"});
    model.component("comp1").geom("geom1").feature("cylsel1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature().duplicate("cylsel2", "cylsel1");
    model.component("comp1").geom("geom1").feature("cylsel2").set("pos", new String[]{"Lxy/2", "0", "Lz-Lz1/2"});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp1").physics("shell").feature("to1").set("d", "thickness");
    model.component("comp1").physics("shell").create("srig1", "RigidConnectorShell", 1);
    model.component("comp1").physics("shell").feature("srig1").selection().named("geom1_cylsel1");
    model.component("comp1").physics("shell").feature("srig1").setIndex("Direction", true, 0);
    model.component("comp1").physics("shell").feature("srig1").setIndex("Direction", true, 1);
    model.component("comp1").physics("shell").feature("srig1").setIndex("Direction", true, 2);
    model.component("comp1").physics("shell").feature("srig1").set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("shell").feature("srig1").setIndex("ConstrainedRotation", true, 2);
    model.component("comp1").physics("shell").feature("srig1").setIndex("ConstrainedRotation", true, 0);
    model.component("comp1").physics("shell").feature().duplicate("srig2", "srig1");
    model.component("comp1").physics("shell").feature("srig2").selection().named("geom1_cylsel2");
    model.component("comp1").physics("shell").feature("srig2").setIndex("Direction", false, 2);
    model.component("comp1").physics("shell").feature("srig2").create("rf1", "RigidBodyForce", -1);
    model.component("comp1").physics("shell").feature("srig2").feature("rf1")
         .set("Ft", new String[]{"0", "0", "-Fload"});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("geom1_boxsel2");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(3, 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().remaining();
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").massProp().create("mass1", "MassProperties");
    model.component("comp1").massProp("mass1").selection().geom("geom1", 2);
    model.component("comp1").massProp("mass1").selection().all();
    model.component("comp1").massProp("mass1").set("densitySource", "fromPhysics");

    model.study("std1").label("\u521d\u59cb\u8bbe\u8ba1");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("frametype", "spatial");
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
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").label("\u632f\u578b (shell)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").set("data", "dset1shellshl");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8d28\u91cf\u548c\u4e34\u754c\u8f7d\u8377\u56e0\u5b50");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"mass1.mass"});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{"\u8d28\u91cf"});
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"mass1.mass", "shell.LFcrit"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u8d28\u91cf", "\u4e34\u754c\u8f7d\u8377\u56e0\u5b50"});
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u632f\u578b\uff0c\u521d\u59cb\u8bbe\u8ba1");

    model.param().set("M0", "23.5[kg]");
    model.param().descr("M0", "\u521d\u59cb\u8d28\u91cf");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("solnum", "auto");
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std2").create("buckling", "LinearBuckling");
    model.study("std2").feature("buckling").set("plotgroup", "Default");
    model.study("std2").feature("buckling").set("neigsactive", true);
    model.study("std2").feature("buckling").set("solnum", "auto");
    model.study("std2").feature("buckling").set("notsolnum", "auto");
    model.study("std2").feature("buckling").set("outputmap", new String[]{});
    model.study("std2").feature("buckling").set("ngenAUX", "1");
    model.study("std2").feature("buckling").set("goalngenAUX", "1");
    model.study("std2").feature("buckling").set("ngenAUX", "1");
    model.study("std2").feature("buckling").set("goalngenAUX", "1");
    model.study("std2").feature("buckling").setSolveFor("/physics/shell", true);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "Lz", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "Lz", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("plistarr", "4 6", 0);
    model.study("std2").label("\u4f18\u5316");
    model.study("std2").create("opt", "Optimization");
    model.study("std2").feature("opt").set("optsolver", "cobyla");
    model.study("std2").feature("opt").setIndex("optobj", "abs(comp1.shell.LFcrit)", 0);
    model.study("std2").feature("opt").set("objectivetype", "maximization");
    model.study("std2").feature("opt").set("objectivesolution", "min");
    model.study("std2").feature("opt").setIndex("pname", "Lz", 0);
    model.study("std2").feature("opt").setIndex("initval", "6[m]", 0);
    model.study("std2").feature("opt").setIndex("scale", 1, 0);
    model.study("std2").feature("opt").setIndex("lbound", "", 0);
    model.study("std2").feature("opt").setIndex("ubound", "", 0);
    model.study("std2").feature("opt").setIndex("pname", "Lz", 0);
    model.study("std2").feature("opt").setIndex("initval", "6[m]", 0);
    model.study("std2").feature("opt").setIndex("scale", 1, 0);
    model.study("std2").feature("opt").setIndex("lbound", "", 0);
    model.study("std2").feature("opt").setIndex("ubound", "", 0);
    model.study("std2").feature("opt").setIndex("pname", "Lxy", 1);
    model.study("std2").feature("opt").setIndex("initval", "5[cm]", 1);
    model.study("std2").feature("opt").setIndex("scale", 1, 1);
    model.study("std2").feature("opt").setIndex("lbound", "", 1);
    model.study("std2").feature("opt").setIndex("ubound", "", 1);
    model.study("std2").feature("opt").setIndex("pname", "Lxy", 1);
    model.study("std2").feature("opt").setIndex("initval", "5[cm]", 1);
    model.study("std2").feature("opt").setIndex("scale", 1, 1);
    model.study("std2").feature("opt").setIndex("lbound", "", 1);
    model.study("std2").feature("opt").setIndex("ubound", "", 1);
    model.study("std2").feature("opt").setIndex("pname", "", 0);
    model.study("std2").feature("opt").setIndex("initval", "5[cm]", 0);
    model.study("std2").feature("opt").setIndex("scale", "5[cm]", 0);
    model.study("std2").feature("opt").setIndex("lbound", "1[cm]", 0);
    model.study("std2").feature("opt").setIndex("ubound", "20[cm]", 0);
    model.study("std2").feature("opt").setIndex("pname", "thickness", 1);
    model.study("std2").feature("opt").setIndex("pname", "Lxy", 0);
    model.study("std2").feature("opt").setIndex("scale", "5[mm]", 1);
    model.study("std2").feature("opt").setIndex("lbound", "1[mm]", 1);
    model.study("std2").feature("opt").setIndex("ubound", "20[mm]", 1);
    model.study("std2").feature("opt").set("constraintExpression", new String[]{"comp1.mass1.mass"});
    model.study("std2").feature("opt").setIndex("constraintExpression", "comp1.mass1.mass/M0", 0);
    model.study("std2").feature("opt").setIndex("constraintUbound", "Lz/(6[m])", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std2");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol5");

    return model;
  }

  public static Model run2(Model model) {
    model.batch("p2").run("compute");

    model.result().dataset("dset5").set("frametype", "spatial");
    model.result().dataset().create("dset5shellshl", "Shell");
    model.result().dataset("dset5shellshl").set("data", "dset5");
    model.result().dataset("dset5shellshl").setIndex("topconst", "1", 3, 1);
    model.result().dataset("dset5shellshl").setIndex("bottomconst", "-1", 3, 1);
    model.result().dataset("dset5shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset5shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset5shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset5shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset5shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset5shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset5shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset5shellshl").set("seplevels", false);
    model.result().dataset("dset5shellshl").set("resolution", 2);
    model.result().dataset("dset5shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset5shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset5shellshl");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").label("\u632f\u578b (shell)");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").set("data", "dset5shellshl");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg2").run();

    model.study("std2").feature("opt").set("probewindow", "");

    model.result("pg2").label("\u632f\u578b\uff0c\u4f18\u5316");
    model.result("pg2").run();
    model.result().evaluationGroup("eg1").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg1").feature("gev2").set("data", "dset5");
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u7f29\u7565\u56fe");
    model.result("pg3").set("data", "dset1shellshl");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("data", "dset5shellshl");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").run();
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("data", "dset5shellshl");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "black");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").create("trn1", "Transformation");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("trn1").set("move", new double[]{-0.25, 0, 0});
    model.result("pg3").feature("surf2").feature("trn1").set("applytodatasetedges", false);
    model.result("pg3").run();
    model.result("pg3").feature("line1").feature().copy("trn1", "pg3/surf2/trn1");
    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u6700\u5927\u9650\u5ea6\u5730\u589e\u52a0\u659c\u6491\u7684\u5c48\u66f2\u8f7d\u8377");

    model
         .description("\u5c48\u66f2\u662f\u7ec6\u957f\u7ed3\u6784\u4ef6\u7684\u5e38\u89c1\u6545\u969c\u6a21\u5f0f\u3002\u8be5\u6a21\u578b\u5728\u7ea6\u675f\u659c\u6491\u4f53\u79ef\u7684\u540c\u65f6\uff0c\u6700\u5927\u9650\u5ea6\u5730\u63d0\u9ad8\u4e86\u659c\u6491\u7684\u5c48\u66f2\u8f7d\u8377\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("diagonal_brace_buckling_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
