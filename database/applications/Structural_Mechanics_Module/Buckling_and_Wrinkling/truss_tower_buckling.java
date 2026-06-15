/*
 * truss_tower_buckling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:01 by COMSOL 6.3.0.290. */
public class truss_tower_buckling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Buckling_and_Wrinkling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("truss", "Truss", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/truss", true);
    model.study("std1").create("buckling", "LinearBuckling");
    model.study("std1").feature("buckling").set("neigsactive", true);
    model.study("std1").feature("buckling").set("solnum", "auto");
    model.study("std1").feature("buckling").set("notsolnum", "auto");
    model.study("std1").feature("buckling").set("outputmap", new String[]{});
    model.study("std1").feature("buckling").set("ngenAUX", "1");
    model.study("std1").feature("buckling").set("goalngenAUX", "1");
    model.study("std1").feature("buckling").set("ngenAUX", "1");
    model.study("std1").feature("buckling").set("goalngenAUX", "1");
    model.study("std1").feature("buckling").setSolveFor("/physics/truss", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("depth", "0.4[m]", "\u5854\u6df1\u5ea6");
    model.param().set("width", "0.45[m]", "\u5854\u5bbd\u5ea6");
    model.param().set("height", "1[m]", "\u5854\u9ad8\u5ea6");
    model.param().set("n", "10", "\u622a\u9762\u6570");
    model.param().set("L", "height*(2*n-1)", "\u5854\u603b\u9ad8\u5ea6");
    model.param().set("do1", "3[cm]", "\u7ba1 1 \u7684\u5916\u5f84");
    model.param().set("di1", "2[cm]", "\u7ba1 1 \u7684\u5185\u5f84");
    model.param().set("do2", "2[cm]", "\u7ba1 2 \u7684\u5916\u5f84");
    model.param().set("di2", "1.4[cm]", "\u7ba1 2 \u7684\u5185\u5f84");
    model.param().set("A1", "pi/4*(do1^2-di1^2)", "\u7ba1 1 \u7684\u9762\u79ef");
    model.param().set("A2", "pi/4*(do2^2-di2^2)", "\u7ba1 2 \u7684\u9762\u79ef");
    model.param().create("par2");
    model.param("par2").label("\u8f7d\u8377");
    model.param("par2").set("I1", "4*A1*(depth/2)^2");
    model.param("par2").descr("I1", "\u5f31\u65b9\u5411\u9762\u79ef\u60ef\u6027\u77e9");
    model.param("par2").set("Fc1", "pi^2*200e9[Pa]*I1/(2*L)^2");
    model.param("par2").descr("Fc1", "\u7b2c\u4e00\u4e2a\u4e34\u754c\u5c48\u66f2\u8f7d\u8377");
    model.param("par2").set("I2", "4*A1*(width/2)^2");
    model.param("par2").descr("I2", "\u521a\u5ea6\u8f83\u5927\u65b9\u5411\u9762\u79ef\u60ef\u6027\u77e9");
    model.param("par2").set("Fc2", "pi^2*200e9[Pa]*I2/(2*L)^2");
    model.param("par2").descr("Fc2", "\u7b2c\u4e8c\u4e2a\u4e34\u754c\u5c48\u66f2\u8f7d\u8377");
    model.param("par2").set("load", "1[N]");
    model.param("par2").descr("load", "\u5916\u52a0\u8f7d\u8377");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"width", "depth", "height"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("ccur1", "ConvertToCurve");
    model.component("comp1").geom("geom1").feature("ccur1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").run("ccur1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "closed");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "depth", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "height", 1, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "width", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "width", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "depth", 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "height", 3, 2);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"0", "depth", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"width", "0", "0"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"0", "0", "height"});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"width", "depth", "height"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("ccur1", "ls1", "ls2", "pol1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"0", "0", "height"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("ccur1", "ls1", "ls2", "pol1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"1", "1", "n"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "0", "2*height"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("mir1(1)", "mir1(2)", "mir1(4)");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new String[]{"1", "1", "n-1"});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"0", "0", "2*height"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5782\u76f4\u8fb9");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(1, 108, 176, 234);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u6a2a\u5411\u8fb9");
    model.component("comp1").selection("com1").set("entitydim", 1);
    model.component("comp1").selection("com1").set("input", new String[]{"sel1"});

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

    model.component("comp1").physics("truss").feature("csd1").set("SectionType", "PipeSection");
    model.component("comp1").physics("truss").feature("csd1").set("do_pipe", "do1");
    model.component("comp1").physics("truss").feature("csd1").set("di_pipe", "di1");
    model.component("comp1").physics("truss").create("csd2", "CrossSectionTruss", 1);
    model.component("comp1").physics("truss").feature("csd2").selection().named("com1");
    model.component("comp1").physics("truss").feature("csd2").set("SectionType", "PipeSection");
    model.component("comp1").physics("truss").feature("csd2").set("do_pipe", "do2");
    model.component("comp1").physics("truss").feature("csd2").set("di_pipe", "di2");
    model.component("comp1").physics("truss").create("pin1", "Pinned", 0);
    model.component("comp1").physics("truss").feature("pin1").selection().set(1, 21, 41, 61);
    model.component("comp1").physics("truss").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("truss").feature("pl1").selection().set(20, 40, 60, 80);
    model.component("comp1").physics("truss").feature("pl1").set("forceType", "TotalForce");
    model.component("comp1").physics("truss").feature("pl1").set("force", new String[]{"0", "0", "-load"});

    model.study("std1").feature("buckling").set("neigs", 2);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("frametype", "spatial");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"truss.disp"});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("colortabletrans", "none");
    model.result("pg1").feature("line1").set("colorscalemode", "linear");
    model.result("pg1").label("\u632f\u578b (truss)");
    model.result("pg1").feature("line1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "truss.re");
    model.result("pg1").feature("line1").set("resolution", "extrafine");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("tuberadiusscale", 4);

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u5c40\u90e8\u5c48\u66f2");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", "truss.lbf_i");
    model.result("pg2").feature("line1").set("descr", "\u5c40\u90e8\u5c48\u66f2\u5931\u6548\u6307\u6570");
    model.result("pg2").feature("line1").set("colorlegend", false);
    model.result("pg2").feature("line1").set("smooth", "none");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("radiusexpr", "truss.re");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("tuberadiusscale", 2);
    model.result("pg2").run();
    model.result("pg2").create("mml1", "MaxMinLine");
    model.result("pg2").feature("mml1").set("expr", "truss.lbs_f");
    model.result("pg2").feature("mml1").set("descr", "\u5c40\u90e8\u5c48\u66f2\u5b89\u5168\u7cfb\u6570");
    model.result("pg2").feature("mml1").set("display", "min");
    model.result("pg2").feature("mml1").set("labelprefix", "\u5b89\u5168\u7cfb\u6570 ");
    model.result("pg2").run();

    model.component("comp1").common().create("bcki1", "BucklingImperfection");
    model.component("comp1").common("bcki1").setIndex("ModesScales", "1e3", 0, 1);
    model.component("comp1").common().create("pres_truss", "PrescribedDeformationDeformedGeometry");
    model.component("comp1").common("pres_truss").label("\u6307\u5b9a\u53d8\u5f62, \u6841\u67b6");
    model.component("comp1").common("pres_truss").selection().geom(1);
    model.component("comp1").common("pres_truss").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252);
    model.component("comp1").common("pres_truss")
         .set("prescribedDeformation", new String[]{"bcki1.dtrussX", "bcki1.dtrussY", "bcki1.dtrussZ"});

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledcommon", new String[]{"pres_truss"});
    model.study("std1").feature("buckling").set("useadvanceddisable", true);
    model.study("std1").feature("buckling").set("disabledcommon", new String[]{"pres_truss"});
    model.study().create("std2");
    model.study("std2").create("stat1", "Stationary");
    model.study("std2").feature("stat1").set("geometricNonlinearity", true);
    model.study("std2").feature("stat1").set("useparam", true);
    model.study("std2").feature("stat1").set("pname", new String[]{});
    model.study("std2").feature("stat1").set("plistarr", new int[]{});
    model.study("std2").feature("stat1").set("punit", new String[]{});
    model.study("std2").feature("stat1").setIndex("pname", "load", 0);
    model.study("std2").feature("stat1").setIndex("plistarr", "84820.2030413834*log(range(1,1,20))/log(15)", 0);
    model.study("std2").feature("stat1").setIndex("punit", "N", 0);

    model.component("comp1").common("bcki1").set("NonlinearBucklingStudy", "std2");

    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("s1").feature("fc1").set("dtech", "const");

    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 20, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"truss.misesGp"});
    model.result("pg3").feature("line1").set("threshold", "manual");
    model.result("pg3").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("colortabletrans", "none");
    model.result("pg3").feature("line1").set("colorscalemode", "linear");
    model.result("pg3").label("\u5e94\u529b (truss)");
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "truss.re");
    model.result("pg3").feature("line1").set("resolution", "extrafine");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("line1").set("tuberadiusscale", 1);
    model.result("pg3").feature("line1").set("tubeendcaps", false);
    model.result("pg3").feature("line1").create("def", "Deform");
    model.result("pg3").feature("line1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("line1").feature("def").set("scale", "1");
    model.result("pg3").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 20, 0);
    model.result("pg4").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "line");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 15, 0);
    model.result("pg3").run();
    model.result("pg3").feature("line1").set("unit", "MPa");
    model.result("pg3").feature("line1").set("tuberadiusscale", 4);
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 20, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"truss.Nxl"});
    model.result("pg5").feature("line1").set("threshold", "manual");
    model.result("pg5").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("line1").set("colortable", "Wave");
    model.result("pg5").feature("line1").set("colortabletrans", "none");
    model.result("pg5").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("radiusexpr", "truss.re");
    model.result("pg5").feature("line1").set("resolution", "extrafine");
    model.result("pg5").feature("line1").set("smooth", "internal");
    model.result("pg5").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg5").feature("line1").set("tuberadiusscale", 1);
    model.result("pg5").feature("line1").set("tubeendcaps", false);
    model.result("pg5").feature("line1").create("def", "Deform");
    model.result("pg5").feature("line1").feature("def").set("scaleactive", true);
    model.result("pg5").feature("line1").feature("def").set("scale", "1");
    model.result("pg5").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg5").label("\u529b (truss)");
    model.result("pg5").label("\u529b (truss)");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 15, 0);
    model.result("pg5").run();
    model.result("pg5").feature("line1").set("tuberadiusscale", 4);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset3");
    model.result("pg6").label("\u540e\u5c48\u66f2\u4f4d\u79fb");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").selection().set(20);
    model.result("pg6").feature("ptgr1").set("expr", "v");
    model.result("pg6").feature("ptgr1").set("linemarker", "point");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u540e\u5c48\u66f2\u5e94\u529b");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr1").selection().set(1);
    model.result("pg7").feature("ptgr1").set("expr", "truss.misesGp");
    model.result("pg7").feature("ptgr1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg7").feature("ptgr1").set("unit", "MPa");
    model.result("pg7").run();
    model.result("pg1").run();
    model.result("pg1").stepNext(0);
    model.result("pg1").run();

    model.title("\u6841\u67b6\u5854\u5c48\u66f2\u5206\u6790");

    model
         .description("\u672c\u4f8b\u9996\u5148\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u7ebf\u6027\u5c48\u66f2\u5206\u6790\u6765\u8ba1\u7b97\u6841\u67b6\u5854\u7684\u4e34\u754c\u5c48\u66f2\u8f7d\u8377\uff0c\u5176\u4e2d\u4f7f\u7528\u201c\u6841\u67b6\u201d\u63a5\u53e3\u5bf9\u5854\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u5c06\u89e3\u4e0e\u901a\u8fc7\u6b27\u62c9\u5c48\u66f2\u5f97\u5230\u7684\u4e34\u754c\u8f7d\u8377\u7684\u89e3\u6790\u8ba1\u7b97\u503c\u8fdb\u884c\u6bd4\u8f83\u3002\n\n\u5728\u7b2c\u4e8c\u6b65\u4e2d\uff0c\u6211\u4eec\u6839\u636e\u5148\u524d\u7814\u7a76\u4e2d\u7684\u51e0\u4f55\u7f3a\u9677\u8fdb\u884c\u975e\u7ebf\u6027\u5c48\u66f2\u5206\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("truss_tower_buckling.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
