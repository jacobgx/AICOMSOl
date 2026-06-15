/*
 * pipe_flow_stress.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:14 by COMSOL 6.3.0.290. */
public class pipe_flow_stress {

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

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Xb", "10[m]", "\u5f2f\u66f2 x \u5750\u6807");
    model.param().set("rb", "2[m]", "\u5f2f\u66f2\u534a\u5f84");
    model.param().set("Xs", "5[m]", "\u652f\u6491 1\uff0cx \u5750\u6807");
    model.param().set("Ys", "5[m]", "\u652f\u6491 2\uff0cy \u5750\u6807");
    model.param().set("Yout", "10[m]", "\u51fa\u53e3 y \u5750\u6807");
    model.param().set("di", "9.2[cm]", "\u7ba1\u5185\u5f84");
    model.param().set("do", "10[cm]", "\u7ba1\u5916\u5f84");
    model.param().set("pin", "50[bar]", "\u7ba1\u9053\u5165\u53e3\u538b\u529b");
    model.param().set("pout", "40[bar]", "\u7ba1\u9053\u51fa\u53e3\u538b\u529b");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "Xs", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "Xb-rb", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("r", "rb");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .set("center", new String[]{"Xb-rb", "rb"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("angle1", 270);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("angle2", 315);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ca2", "ca1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2").set("angle1", 315);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2").set("angle2", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ca2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", "Xb", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", "rb", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", "Xb", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", "Ys", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", "Xb", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", "Yout", 2, 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.02);
    model.component("comp1").mesh("mesh1").run();

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
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat2").label("Water, liquid");
    model.component("comp1").material("mat2").set("family", "water");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
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
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").selection().all();

    model.component("comp1").physics("pfl").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("pfl").feature("pipe1").set("innerd", "di");
    model.component("comp1").physics("pfl").feature("pipe1").set("frictionmodel", 6);
    model.component("comp1").physics("pfl").feature("pipe1").set("roughness", 3);
    model.component("comp1").physics("pipem").feature("fpm1").set("FluidMaterial", "mat2");
    model.component("comp1").physics("pipem").feature("fpm1").set("PipeMaterial", "mat1");
    model.component("comp1").physics("pipem").feature("pcs1").set("shape", "Round");
    model.component("comp1").physics("pipem").feature("pcs1").set("do_pipe", "do");
    model.component("comp1").physics("pipem").feature("pcs1").set("di_pipe", "di");
    model.component("comp1").physics("pipem").feature("pcs1").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("pipem").feature("pcs1").feature("so1").set("vector_beam", new int[]{-1, 1, 0});
    model.component("comp1").physics("pipem").feature("pcs1").create("bend1", "BendCorrection", 1);
    model.component("comp1").physics("pipem").feature("pcs1").feature("bend1").selection().set(3, 4);
    model.component("comp1").physics("pipem").feature("pcs1").feature("bend1").set("kFi", "1.65/pipem.hFlex");
    model.component("comp1").physics("pipem").feature("pcs1").feature("bend1").set("kFo", "1.65/pipem.hFlex");
    model.component("comp1").physics("pipem").feature("pcs1").feature("bend1")
         .set("SIFi", "0.9/(pipem.hFlex)^(2/3)");
    model.component("comp1").physics("pipem").feature("pcs1").feature("bend1")
         .set("SIFo", "0.75/(pipem.hFlex)^(2/3)");
    model.component("comp1").physics("pipem").create("fix1", "Fixed", 0);
    model.component("comp1").physics("pipem").feature("fix1").selection().set(1, 7);
    model.component("comp1").physics("pipem").create("pdr1", "DispRot0", 0);
    model.component("comp1").physics("pipem").feature("pdr1").selection().set(2, 4, 6);
    model.component("comp1").physics("pipem").feature("pdr1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("pipem").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("pfl").feature("pr1").label("\u538b\u529b\uff0c\u5165\u53e3");
    model.component("comp1").physics("pfl").feature("pr1").set("p0", "pin");
    model.component("comp1").physics("pfl").create("pr2", "Pressure", 0);
    model.component("comp1").physics("pfl").feature("pr2").label("\u538b\u529b\uff0c\u51fa\u53e3");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("pfl").feature("pr2").selection().set(7);
    model.component("comp1").physics("pfl").feature("pr2").set("p0", "pout");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

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
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"pressure", "\u538b\u529b", "Pa", "Pa"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "bar", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u4f4d\u79fb");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u4f4d\u79fb [mm]");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().all();
    model.result("pg4").feature("lngr1").set("expr", "w_pipe");
    model.result("pg4").feature("lngr1").set("unit", "mm");
    model.result("pg4").feature("lngr1").set("titletype", "none");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "\u5782\u76f4\u4f4d\u79fb", 0);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("expr", "u_pipe*pipem.beamsys.e_y1+v_pipe*pipem.beamsys.e_y2");
    model.result("pg4").feature("lngr2").setIndex("legends", "\u5411\u5185\u4f4d\u79fb", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u5f2f\u77e9");
    model.result("pg5").set("ylabel", "\u529b\u77e9 [N*m]");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("expr", "pipem.Myl");
    model.result("pg5").feature("lngr1").setIndex("legends", "\u9762\u5916\u5f2f\u77e9", 0);
    model.result("pg5").feature("lngr1").set("resolution", "norefine");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("expr", "pipem.Mzl");
    model.result("pg5").feature("lngr2").setIndex("legends", "\u9762\u5185\u5f2f\u77e9", 0);
    model.result("pg5").feature("lngr2").set("resolution", "norefine");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u526a\u529b");
    model.result("pg6").set("ylabel", "\u529b [N]");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").set("expr", "pipem.Tzl");
    model.result("pg6").feature("lngr1").setIndex("legends", "\u9762\u5916\u526a\u529b", 0);
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "pipem.Tyl");
    model.result("pg6").feature("lngr2").setIndex("legends", "\u9762\u5185\u526a\u529b", 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5e94\u529b\u8d21\u732e");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u5e94\u529b [MPa]");
    model.result("pg7").set("legendpos", "middleright");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().all();
    model.result("pg7").feature("lngr1").set("expr", "pipem.shm");
    model.result("pg7").feature("lngr1").set("titletype", "none");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "\u73af\u5411\u5e94\u529b", 0);
    model.result("pg7").feature().duplicate("lngr2", "lngr1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").set("expr", "pipem.sn");
    model.result("pg7").feature("lngr2").setIndex("legends", "\u8f74\u5411\u5e94\u529b", 0);
    model.result("pg7").feature().duplicate("lngr3", "lngr2");
    model.result("pg7").run();
    model.result("pg7").feature("lngr3").set("expr", "pipem.sb1");
    model.result("pg7").feature("lngr3").setIndex("legends", "\u5f2f\u66f2\u5e94\u529b\uff0c\u9762\u5185", 0);
    model.result("pg7").feature().duplicate("lngr4", "lngr3");
    model.result("pg7").run();
    model.result("pg7").feature("lngr4").set("expr", "pipem.sb2");
    model.result("pg7").feature("lngr4").setIndex("legends", "\u5f2f\u66f2\u5e94\u529b\uff0c\u9762\u5916", 0);
    model.result("pg7").feature().duplicate("lngr5", "lngr4");
    model.result("pg7").run();
    model.result("pg7").feature("lngr5").set("expr", "sqrt(pipem.txymax^2+pipem.txzmax^2)");
    model.result("pg7").feature("lngr5").setIndex("legends", "\u526a\u5207\u5e94\u529b", 0);
    model.result("pg7").feature().duplicate("lngr6", "lngr5");
    model.result("pg7").run();
    model.result("pg7").feature("lngr6").set("expr", "pipem.mises");
    model.result("pg7").feature("lngr6").setIndex("legends", "von Mises \u5e94\u529b", 0);
    model.result("pg7").run();
    model.result("pg3").run();

    model.title("\u7ba1\u9053\u5185\u6d41\u52a8\u4e0e\u5e94\u529b\u7684\u8026\u5408\u5206\u6790");

    model
         .description("\u5728\u672c\u6559\u7a0b\u4e2d\uff0c\u6211\u4eec\u4f7f\u7528\u201c\u7ba1\u9053\u6d41\u201d\u63a5\u53e3\u8ba1\u7b97\u5e26\u6709\u5f2f\u5934\u7684\u7ba1\u9053\u4e2d\u7684\u6d41\u52a8\u3002\u8ba1\u7b97\u51fa\u7684\u6d41\u4f53\u6d41\u52a8\u8f7d\u8377\u7528\u4f5c\u201c\u7ba1\u529b\u5b66\u201d\u63a5\u53e3\u4e2d\u5e94\u529b\u5206\u6790\u7684\u8f93\u5165\u3002\u8fd8\u5206\u6790\u4e86\u6765\u81ea\u7ba1\u9053\u548c\u6d41\u4f53\u7684\u91cd\u529b\u8f7d\u8377\u3002");

    model.label("pipe_flow_stress.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
