/*
 * cooling_pipeline_stress.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:14 by COMSOL 6.3.0.290. */
public class cooling_pipeline_stress {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Pipe_Mechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("pfl", "FlowInPipes", "geom1");
    model.component("comp1").physics("pfl").field("velocity").field("u_fluid");
    model.component("comp1").physics().create("pipem", "PipeMechanics", "geom1");
    model.component("comp1").physics("pipem").field("displacement").field("u_pipe");
    model.component("comp1").physics("pipem").field("displacement")
         .component(new String[]{"u_pipe", "v_pipe", "w_pipe"});
    model.component("comp1").physics("pipem").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");

    model.component("comp1").multiphysics().create("fpipe1", "FluidPipeInteraction", 1);
    model.component("comp1").multiphysics("fpipe1").set("Fluid_physics", "pfl");
    model.component("comp1").multiphysics("fpipe1").set("Structure_physics", "pipem");
    model.component("comp1").multiphysics("fpipe1").set("CouplingType", "FluidLoading");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/pfl", true);
    model.study("std1").feature("stat").setSolveFor("/physics/pipem", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/fpipe1", true);

    model.param().set("d1", "10[cm]");
    model.param().descr("d1", "\u7ba1\u5f84 1");
    model.param().set("d2", "20[cm]");
    model.param().descr("d2", "\u7ba1\u5f84 2");
    model.param().set("Q", "300[W/m]");
    model.param().descr("Q", "\u70ed\u6e90");
    model.param().set("Text", "20[degC]");
    model.param().descr("Text", "\u5916\u90e8\u6e29\u5ea6");
    model.param().set("dp", "0.5[atm]");
    model.param().descr("dp", "\u538b\u529b\u589e\u52a0");
    model.param().set("hpw", "3[mm]");
    model.param().descr("hpw", "\u7ba1\u58c1\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("table", new double[][]{{0, -0.55, 3.15}, {0, -0.55, 7.65}, {10.75, -0.55, 7.65}, {10.75, -11.3, 7.65}, {10.75, -11.3, 9}, {-20.3, -11.3, 9}, {-20.3, 12.4, 9}, {23.5, 12.4, 9}, {23.5, -10.55, 9}, {15.2, -10.55, 9}, {15.2, 1.85, 9}, {18.2, 1.85, 9}, {18.2, -8.95, 9}, {21.35, -8.95, 9}, {21.35, 11.45, 9}, {-16.35, 11.45, 9}, {-16.35, -9.15, 9}, {10.25, -9.15, 9}, {10.25, -1.45, 9}, {6.56, -1.45, 9}, {6.56, -3.75, 9}, {7.86, -3.75, 9}, {7.86, -7.9, 9}, {-13.75, -7.9, 9}, {-13.75, 9.4, 9}, {-10.85, 9.4, 9}, {-10.85, -4.75, 9}, {-4, -4.75, 9}, {-4, 9.4, 9}, {18.65, 9.4, 9}, {18.65, 4.45, 9}, {0, 4.45, 9}, {0, -5.3, 9}, {0, -5.3, 3.15}});
    model.component("comp1").geom("geom1").feature("pol1").set("selresult", true);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u4e3b\u7ba1\u9053");
    model.component("comp1").geom("geom1").feature("pol1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2")
         .set("table", new double[][]{{0, -8, 3.15}, {0, -6.65, 3.15}, {0, -5.3, 3.15}, {0, -6.65, 3.15}, {0, -6.65, 3.15}, {-14.339, -6.65, 3.15}, {-15.24, 7, 1.85}, {-18.45, 7, 1.85}, {-19.8, 8.6, 3.15}});
    model.component("comp1").geom("geom1").feature("pol2").set("selresult", true);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u5165\u53e3\u7ba1\u6bb5");
    model.component("comp1").geom("geom1").feature("pol2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3")
         .set("table", new double[][]{{-19.8, 8.6, 3.15}, {-21.1, 8.6, 3.15}, {-21.1, 10.65, 3.15}, {0, 10.65, 3.15}, {0, -0.55, 3.15}});
    model.component("comp1").geom("geom1").feature("pol3").set("selresult", true);
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u51fa\u53e3\u7ba1\u6bb5");
    model.component("comp1").geom("geom1").feature("pol3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").create("pol4", "Polygon");
    model.component("comp1").geom("geom1").feature("pol4").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol4")
         .set("table", new double[][]{{0, 5.55, 3.15}, {0, 5.55, 7.65}, {-11.75, 5.55, 7.65}, {-11.75, 5.55, 4.65}, {-16.75, 5.55, 4.65}, {-16.75, 5.55, 7.65}, {-11.75, 4.05, 7.65}, {-11.75, 4.05, 4.65}, {-16.75, 4.05, 4.65}, {-16.75, 4.05, 7.65}, {-11.75, 2.55, 7.65}, {-11.75, 2.55, 4.65}, {-16.75, 2.55, 4.65}, {-16.75, 2.55, 7.65}, {-11.75, 1.05, 7.65}, {-11.75, 1.05, 4.65}, {-16.75, 1.05, 4.65}, {-16.75, 1.05, 7.65}, {-11.75, -0.45, 7.65}, {-11.75, -0.45, 4.65}, {-16.75, -0.45, 4.65}, {-16.75, -0.45, 7.65}, {-11.75, -1.95, 7.65}, {-11.75, -1.95, 4.65}, {-16.75, -1.95, 4.65}, {-16.75, -1.95, 7.65}, {0, -8, 7.65}, {0, -8, 3.15}});
    model.component("comp1").geom("geom1").feature("pol4").set("selresult", true);
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u52a0\u70ed\u5668\u7ba1\u6bb5");
    model.component("comp1").geom("geom1").feature("pol4").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("pol4");
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("pol1", 1, 2, 3, 6, 32, 33);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", 0.25, 0);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", 0.5, 1);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", 0.75, 2);
    model.component("comp1").geom("geom1").run("pare1");
    model.component("comp1").geom("geom1").create("pare2", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare2").selection("edge").set("pare1", 7, 8, 11, 20);
    model.component("comp1").geom("geom1").feature("pare2").selection("edge").set("pol3", 3);
    model.component("comp1").geom("geom1").feature("pare2").setIndex("param", "1/3", 0);
    model.component("comp1").geom("geom1").feature("pare2").setIndex("param", "2/3", 1);
    model.component("comp1").geom("geom1").run("pare2");
    model.component("comp1").geom("geom1").create("pare3", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare3").selection("edge").set("pare2(1)", 12, 16, 23, 29);
    model.component("comp1").geom("geom1").feature("pare3").selection("edge").set("pol2", 3, 4);
    model.component("comp1").geom("geom1").feature("pare3").selection("edge").set("pol4", 3);
    model.component("comp1").geom("geom1").run("pare3");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp1").selection("vertex1").set("pare3(3)", 2);
    model.component("comp1").geom("geom1").feature("wp1").selection("vertex2").set("pare3(3)", 25);
    model.component("comp1").geom("geom1").feature("wp1").selection("vertex3").set("pare3(3)", 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").coordSystem().create("sys2", "FromGeometry");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("sys2").set("workplane", "wp1");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("di", "d1");
    model.component("comp1").variable("var1").descr("di", "\u5185\u5f84");
    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().named("geom1_csel1_edg");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").selection().named("geom1_csel4_edg");
    model.component("comp1").variable().duplicate("var3", "var2");
    model.component("comp1").variable("var3").set("di", "d2");
    model.component("comp1").variable("var3").selection().named("geom1_csel2_edg");
    model.component("comp1").variable().duplicate("var4", "var3");
    model.component("comp1").variable("var4").selection().named("geom1_csel3_edg");

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("maxop1").selection().all();

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").label("Air");
    model.component("comp1").material("mat2").set("family", "air");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat2").materialType("nonSolid");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_edg");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat3").label("Water, liquid");
    model.component("comp1").material("mat3").set("family", "water");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat3").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat3").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").selection().all();

    model.component("comp1").physics("pfl").feature("fp1").set("minput_temperature", "50[degC]");
    model.component("comp1").physics("pfl").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("pfl").feature("pipe1").set("innerd", "di");
    model.component("comp1").physics("pfl").feature("pipe1").set("roughness", 3);
    model.component("comp1").physics("pipem").feature("fpm1").set("PipeMaterial", "mat1");
    model.component("comp1").physics("pipem").feature("pcs1").set("shape", "Round");
    model.component("comp1").physics("pipem").feature("pcs1").set("di_pipe", "di");
    model.component("comp1").physics("pipem").feature("pcs1").set("do_pipe", "di+hpw");
    model.component("comp1").physics("pfl").create("vf1", "VolumeForce", 1);
    model.component("comp1").physics("pfl").feature("vf1").selection().all();
    model.component("comp1").physics("pfl").create("pump1", "Pump", 0);
    model.component("comp1").physics("pfl").feature("pump1").selection().set(9);
    model.component("comp1").physics("pfl").feature("pump1").set("pumptype", 1);
    model.component("comp1").physics("pfl").feature("pump1").set("deltap", "dp");
    model.component("comp1").physics("pfl").create("ipl1", "PressureLock", 0);
    model.component("comp1").physics("pfl").feature("ipl1").selection().set(8);
    model.component("comp1").physics("pfl").create("tjunc1", "Tjunction", 0);
    model.component("comp1").physics("pfl").feature("tjunc1").set("selectionControl", false);
    model.component("comp1").physics("pfl").create("bend1", "Bend", 0);
    model.component("comp1").physics("pfl").feature("bend1").selection()
         .set(1, 2, 3, 7, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 25, 26, 28, 30, 32, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 57, 59, 60, 61, 63, 64, 65, 66, 67, 69, 70, 77, 78, 79, 80, 82, 83, 84, 85, 86, 90, 91, 92, 93, 94, 95, 96, 100, 101, 105);
    model.component("comp1").physics("pfl").feature("init1").set("u_fluid", -1);
    model.component("comp1").physics("pipem").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("pipem").create("fix1", "Fixed", 0);
    model.component("comp1").physics("pipem").feature("fix1").selection().set(8);
    model.component("comp1").physics("pipem").create("pdr1", "DispRot0", 0);
    model.component("comp1").physics("pipem").feature("pdr1").selection()
         .set(2, 3, 4, 5, 6, 7, 10, 12, 14, 16, 18, 20, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 36, 38, 40, 42, 44, 46, 47, 48, 49, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 63, 64, 65, 67, 68, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105);
    model.component("comp1").physics("pipem").feature("pdr1").setIndex("Direction", "limited", 2);
    model.component("comp1").physics("pipem").feature("pdr1").setIndex("U0min", 0, 2);
    model.component("comp1").physics("pipem").create("pdr2", "DispRot0", 0);
    model.component("comp1").physics("pipem").feature("pdr2").selection().set(62);
    model.component("comp1").physics("pipem").feature("pdr2").setIndex("Direction", "limited", 0);
    model.component("comp1").physics("pipem").feature("pdr2").setIndex("U0max", 0.01, 0);
    model.component("comp1").physics("pipem").create("pdr3", "DispRot0", 0);
    model.component("comp1").physics("pipem").feature("pdr3").selection().set(50);
    model.component("comp1").physics("pipem").feature("pdr3").set("coordinateSystem", "sys2");
    model.component("comp1").physics("pipem").feature("pdr3").setIndex("Direction", "limited", 1);
    model.component("comp1").physics("pipem").feature("pdr3").setIndex("U0max", 0, 1);
    model.component("comp1").physics("pipem").feature("pdr3").setIndex("Direction", "limited", 2);
    model.component("comp1").physics("pipem").feature("pdr3").setIndex("U0max", 0.01, 2);
    model.component("comp1").physics("pipem").feature("pdr3").setIndex("U0min", -0.01, 2);

    model.study("std1").showAutoSequences("all");

    model.component("comp1").physics().create("htp", "HeatTransferPipes", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/htp", true);

    model.component("comp1").physics("htp").feature("ht1").set("u_src", "root.comp1.u_fluid");
    model.component("comp1").physics("htp").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("htp").feature("pipe1").set("innerd", "di");
    model.component("comp1").physics("htp").feature("pipe1").set("roughness", 3);
    model.component("comp1").physics("htp").create("hs1", "HeatSource", 1);
    model.component("comp1").physics("htp").feature("hs1").selection()
         .set(12, 13, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 44, 45, 46, 47, 48, 49);
    model.component("comp1").physics("htp").feature("hs1").set("Q", "Q");
    model.component("comp1").physics("htp").create("wht1", "WallHeatTransfer", 1);
    model.component("comp1").physics("htp").feature("wht1").selection()
         .set(4, 5, 6, 7, 8, 9, 30, 31, 32, 33, 34, 39, 40, 41, 42, 43, 51, 52, 53, 54, 56, 59, 60, 61, 62, 63, 64, 69, 73, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106);
    model.component("comp1").physics("htp").feature("wht1").set("Text", "Text");
    model.component("comp1").physics("htp").feature("wht1").create("intfilm1", "InternalFilmResistance", 1);
    model.component("comp1").physics("htp").feature("wht1").create("extfilm1", "ExternalFilmResistance", 1);
    model.component("comp1").physics("htp").feature("wht1").feature("extfilm1")
         .set("externalFilmModelNewtonian", "Natural");
    model.component("comp1").physics("htp").feature("wht1").feature("extfilm1").set("externalMaterial", "mat2");
    model.component("comp1").physics("pipem").feature("fpm1").create("te1", "ThermalExpansion", 1);
    model.component("comp1").physics("pipem").feature("fpm1").feature("te1")
         .set("minput_temperature_src", "root.comp1.T");
    model.component("comp1").physics("pipem").feature("fpm1").feature("te1")
         .set("minput_strainreferencetemperature_src", "userdef");
    model.component("comp1").physics("pipem").feature("fpm1").feature("te1")
         .set("minput_strainreferencetemperature", "Text");

    model.sol("sol1").feature("v1").updateVariables();
    model.sol("sol1").feature("s1").feature("se1").feature("htp1").label("\u70ed\u91cf");
    model.sol("sol1").feature("s1").feature("se1").feature("htp1").set("subdtech", "auto");
    model.sol("sol1").feature("s1").feature("se1").feature("htp1").set("subntolfact", 1);
    model.sol("sol1").feature("s1").feature("se1").feature("htp1").set("maxsubiter", 50);
    model.sol("sol1").feature("s1").feature("se1").feature().move("htp1", 1);

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u538b\u529b (pfl)");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "0.5*pfl.dh");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (pfl)");
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").set("arrowcount", 20);
    model.result("pg2").feature("arwl1").set("arrowlength", "normalized");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("col1", "Color");
    model.result("pg2").feature("arwl1").feature("col1").set("showcolordata", "off");
    model.result("pg2").feature("arwl1").feature("col1").set("expr", "pfl.U");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"pipem.mises"});
    model.result("pg3").feature("line1").set("threshold", "manual");
    model.result("pg3").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("colortabletrans", "none");
    model.result("pg3").feature("line1").set("colorscalemode", "linear");
    model.result("pg3").label("\u5e94\u529b (pipem)");
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "pipem.re");
    model.result("pg3").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("line1").set("tuberadiusscale", 1);
    model.result("pg3").feature("line1").create("def", "Deform");
    model.result("pg3").feature("line1").feature("def").set("expr", new String[]{"u_pipe", "v_pipe", "w_pipe"});
    model.result("pg3").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6 (htp)");
    model.result("pg4").feature().create("line1", "Line");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("expr", "T");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("radiusexpr", "0.5*htp.dh");
    model.result("pg4").feature("line1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 1, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg4").feature("line1").set("tuberadiusscale", 3);

    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").feature("stat").set("plotgroup", "pg4");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "d1", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "d1", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "dp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(2,-0.25,0.25)", 0);
    model.study("std1").feature("stat").setIndex("punit", "atm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u6700\u9ad8\u6e29\u5ea6");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "maxop1(T)", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u6700\u9ad8\u6e29\u5ea6", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u6700\u5927 von Mises \u5e94\u529b");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("expr", "maxop1(pipem.mises)", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u6700\u5927 von Mises \u5e94\u529b", 0);
    model.result("pg6").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 7, 0);
    model.result("pg1").run();
    model.result("pg1").feature("line1").set("unit", "atm");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 3);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 7, 0);
    model.result("pg2").run();
    model.result("pg2").feature("arwl1").set("arrowcount", 80);
    model.result("pg2").feature("arwl1").set("arrowtype", "cone");
    model.result("pg2").feature("arwl1").set("arrowbase", "center");
    model.result("pg2").feature("arwl1").set("scaleactive", true);
    model.result("pg2").feature("arwl1").set("scale", 2);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 7, 0);
    model.result("pg3").run();
    model.result("pg3").feature("line1").set("tuberadiusscale", 3);
    model.result("pg3").run();
    model.result("pg3").feature("line1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("line1").feature("def").set("scale", 1);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 7, 0);
    model.result("pg4").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 8, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", new String[]{"pipem.Mtotl"});
    model.result("pg7").feature("line1").set("threshold", "manual");
    model.result("pg7").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("line1").set("colortable", "Spectrum");
    model.result("pg7").feature("line1").set("colortabletrans", "none");
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("radiusexpr", "pipem.re");
    model.result("pg7").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg7").feature("line1").set("tuberadiusscale", 1);
    model.result("pg7").feature("line1").create("def", "Deform");
    model.result("pg7").feature("line1").feature("def").set("expr", new String[]{"u_pipe", "v_pipe", "w_pipe"});
    model.result("pg7").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg7").feature("line1").set("colorscalemode", "linear");
    model.result("pg7").label("Total bending moment (pipem)");
    model.result("pg7").label("Total bending moment (pipem)");
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 7, 0);
    model.result("pg7").run();
    model.result("pg7").feature("line1").set("unit", "kN*m");
    model.result("pg7").feature("line1").set("tuberadiusscale", 3);
    model.result("pg7").run();
    model.result("pg7").feature("line1").feature("def").set("scaleactive", true);
    model.result("pg7").feature("line1").feature("def").set("scale", 1);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 8, 0);
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").create("line1", "Line");
    model.result("pg8").feature("line1").set("expr", new String[]{"pipem.Ttotl"});
    model.result("pg8").feature("line1").set("threshold", "manual");
    model.result("pg8").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg8").feature("line1").set("colortable", "Spectrum");
    model.result("pg8").feature("line1").set("colortabletrans", "none");
    model.result("pg8").feature("line1").set("linetype", "tube");
    model.result("pg8").feature("line1").set("radiusexpr", "pipem.re");
    model.result("pg8").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg8").feature("line1").set("tuberadiusscale", 1);
    model.result("pg8").feature("line1").create("def", "Deform");
    model.result("pg8").feature("line1").feature("def").set("expr", new String[]{"u_pipe", "v_pipe", "w_pipe"});
    model.result("pg8").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg8").feature("line1").set("colorscalemode", "linear");
    model.result("pg8").label("\u603b\u526a\u529b (pipem)");
    model.result("pg8").label("\u603b\u526a\u529b (pipem)");
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 7, 0);
    model.result("pg8").run();
    model.result("pg8").feature("line1").set("unit", "kN");
    model.result("pg8").feature("line1").set("tuberadiusscale", 3);
    model.result("pg8").run();
    model.result("pg8").feature("line1").feature("def").set("scaleactive", true);
    model.result("pg8").feature("line1").feature("def").set("scale", 1);
    model.result("pg8").run();
    model.result().dataset().create("dset1pipem", "Pipe");
    model.result().dataset("dset1pipem").set("data", "dset1");
    model.result().dataset("dset1pipem").set("physicsinterface", "pipem");
    model.result().dataset("dset1pipem").set("refinement", 2);
    model.result().dataset("dset1pipem")
         .set("defaultPlotIDs", new String[]{"stress3D|pipem", "geometryOrientation|pipem"});
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset1pipem");
    model.result("pg9").setIndex("looplevel", 8, 0);
    model.result("pg9").label("\u5e94\u529b\uff0c\u4e09\u7ef4 (pipem)");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"pipem.misesS"});
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg9").feature("surf1").create("def", "Deform");
    model.result("pg9").feature("surf1").feature("def")
         .set("expr", new String[]{"pipem.uS", "pipem.vS", "pipem.wS"});
    model.result("pg9").label("\u5e94\u529b\uff0c\u4e09\u7ef4 (pipem)");
    model.result("pg9").run();
    model.result().dataset("dset1pipem").selection().geom("geom1", 1);
    model.result().dataset("dset1pipem").selection().set(11);
    model.result("pg9").run();

    model.view().create("view3", 3);

    model.result("pg9").run();
    model.result("pg9").set("view", "view3");
    model.result("pg9").run();

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg1").run();

    model.title("\u6563\u70ed\u7ba1\u7f51\u7684\u5e94\u529b");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u7ba1\u7f51\u4e2d\u7684\u6d41\u52a8\u3001\u4f20\u70ed\u3001\u7ed3\u6784\u53d8\u5f62\u548c\u5e94\u529b\u8fdb\u884c\u8026\u5408\u5efa\u6a21\uff1b\u6b64\u5916\uff0c\u8fd8\u5206\u6790\u7ba1\u548c\u6d41\u4f53\u7684\u91cd\u529b\u8f7d\u8377\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("cooling_pipeline_stress.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
