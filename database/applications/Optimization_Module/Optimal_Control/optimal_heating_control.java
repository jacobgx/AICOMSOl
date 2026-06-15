/*
 * optimal_heating_control.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:41 by COMSOL 6.3.0.290. */
public class optimal_heating_control {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Optimal_Control");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/ge", true);

    model.param().set("radius", "5[mm]");
    model.param().descr("radius", "\u5706\u67f1\u534a\u5f84");
    model.param().set("tmax", "10[s]");
    model.param().descr("tmax", "\u4eff\u771f\u65f6\u95f4");
    model.param().set("Pmax", "50[kW]");
    model.param().descr("Pmax", "\u6700\u5927\u529f\u7387");
    model.param().set("Ttarget", "260[degC]");
    model.param().descr("Ttarget", "\u76ee\u6807\u6e29\u5ea6");
    model.param().set("T0", "20[degC]");
    model.param().descr("T0", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("Lz", "3[m]");
    model.param().descr("Lz", "\u9762\u5916\u957f\u5ea6\u5c3a\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "radius", 1);

    model.component("comp1").probe().create("point1", "Point");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").probe("point1").label("\u5916\u90e8\u6e29\u5ea6");
    model.component("comp1").probe("point1").set("probename", "Tout");
    model.component("comp1").probe("point1").selection().set(2);
    model.component("comp1").probe().duplicate("point2", "point1");
    model.component("comp1").probe("point2").label("\u5185\u90e8\u6e29\u5ea6");
    model.component("comp1").probe("point2").set("probename", "Tin");
    model.component("comp1").probe("point2").selection().set(1);
    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").label("\u6e29\u5ea6\u8bef\u5dee");
    model.component("comp1").probe("var1").set("probename", "Terror");
    model.component("comp1").probe("var1").set("expr", "(1-(Tout-T0)/(Ttarget-T0))^2");

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

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");

    model.component("comp1").common().create("cfunc1", "ControlFunction");
    model.component("comp1").common("cfunc1")
         .label("\u63a7\u5236\u51fd\u6570\uff08\u5206\u6bb5\u591a\u9879\u5f0f\uff09");
    model.component("comp1").common("cfunc1").set("xend", "tmax");
    model.component("comp1").common("cfunc1").set("controlType", "piecewiseBernsteinPolynomial");
    model.component("comp1").common("cfunc1").set("order", "3");
    model.component("comp1").common("cfunc1").set("noSegments", "3");
    model.component("comp1").common().duplicate("cfunc2", "cfunc1");
    model.component("comp1").common("cfunc2").label("\u63a7\u5236\u51fd\u6570\uff08\u4ea5\u59c6\u970d\u5179\uff09");
    model.component("comp1").common("cfunc2").set("controlType", "helmholtzFilter");
    model.component("comp1").common("cfunc2").set("fgradmax", "10/tmax");

    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "Lz");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 0);
    model.component("comp1").physics("ht").feature("hf1").selection().set(2);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "HeatRate");
    model.component("comp1").physics("ht").feature("hf1").set("P0", "cfunc1(t)*Pmax");
    model.component("comp1").physics("ht").feature().duplicate("hf2", "hf1");
    model.component("comp1").physics("ht").feature("hf2").set("P0", "cfunc2(t)*Pmax");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "obj", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "objt*tmax-Terror", 0, 0);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u521d\u59cb\u63a7\u5236");
    model.study("std1").feature("time").set("tlist", "range(0,tmax/20,tmax)");
    model.study("std1").feature("time").set("probesel", "none");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"ht/hf2"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "r");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"obj"});
    model.result().numerical("gev1").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj"});
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("expr", new String[]{"obj"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj"});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().remove("lngr1");
    model.result("pg1").run();
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("expr", new String[]{"Tout"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u5916\u90e8\u6e29\u5ea6"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"K"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"Tin"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u5185\u90e8\u6e29\u5ea6"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"K"});
    model.result("pg1").feature("glob1").setIndex("expr", "Tout", 0);
    model.result("pg1").feature("glob1").setIndex("unit", "degC", 0);
    model.result("pg1").feature("glob1").setIndex("expr", "Tin", 1);
    model.result("pg1").feature("glob1").setIndex("unit", "degC", 1);
    model.result("pg1").feature().duplicate("glob2", "glob1");
    model.result("pg1").run();
    model.result("pg1").feature("glob2").set("expr", new String[]{});
    model.result("pg1").feature("glob2").set("descr", new String[]{});
    model.result("pg1").feature("glob2").set("expr", new String[]{"ht.hf1.ntefluxInt"});
    model.result("pg1").feature("glob2").set("descr", new String[]{"\u603b\u51c0\u80fd\u7387"});
    model.result("pg1").feature("glob2").setIndex("expr", "-ht.hf1.ntefluxInt", 0);
    model.result("pg1").feature("glob2").setIndex("unit", "kW", 0);
    model.result("pg1").feature("glob2").setIndex("descr", "\u529f\u7387", 0);
    model.result("pg1").run();
    model.result("pg1").label("\u521d\u59cb\u63a7\u5236");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6e29\u5ea6 (degC)");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg1").set("yseclabelactive", true);
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", "-tmax/50");
    model.result("pg1").set("xmax", "tmax*1.02");
    model.result("pg1").set("ymin", 10);
    model.result("pg1").set("ymax", 320);
    model.result("pg1").set("yminsec", "-Pmax*2e-5");
    model.result("pg1").set("ymaxsec", "Pmax*1.02e-3");
    model.result("pg1").set("legendpos", "middleright");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().remove("pg2");

    model.study().create("std2");
    model.study().create("std3");
    model.study("std2").setGenPlots(false);
    model.study("std2")
         .label("\u7814\u7a76 2\uff1a\u4f18\u5316\uff08\u5206\u6bb5\u4f2f\u6069\u65af\u5766\u591a\u9879\u5f0f\uff09");
    model.study("std2").feature().copy("time", "std1/time");
    model.study("std2").create("opt", "Optimization");
    model.study("std2").feature("opt").set("optsolver", "ipopt");
    model.study("std2").feature("opt").set("nsolvemax", 50);
    model.study("std2").feature("opt").set("optobj", new String[]{"comp1.obj"});
    model.study("std2").feature("opt").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj"});
    model.study("std2").feature("opt").setEntry("controlVariableActive", "cfunc2.funcc", false);
    model.study("std2").feature("opt").set("probesel", "none");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u5206\u6bb5\u4f2f\u6069\u65af\u5766\u591a\u9879\u5f0f");
    model.result("pg2").set("data", "dset2");

    model.study("std2").feature("opt").set("plot", true);
    model.study("std2").feature("opt").set("plotgroup", "pg2");
    model.study("std2").createAutoSequences("all");
    model.study("std2").feature("opt").set("continuecontrolparams", new String[]{});
    model.study("std2").feature("opt").set("continuecontrolvals", new double[]{});
    model.study("std2").feature("opt").set("continuelagrangevals", new double[]{});
    model.study("std2").feature("opt").set("continuelagrangeparams", new String[]{});

    model.sol("sol2").runAll();

    model.result("pg2").run();

    model.study("std2").feature("opt").set("probewindow", "");
    model.study("std3").feature().copy("opt", "std2/opt");
    model.study("std3").feature().copy("time", "std2/time");
    model.study("std3").feature("opt").set("optsolver", "mma");
    model.study("std3").feature("opt").set("opttolinner", 0.01);
    model.study("std3").feature("opt").setEntry("controlVariableActive", "cfunc1", false);
    model.study("std3").feature("opt").setEntry("controlVariableActive", "cfunc2.funcc", true);
    model.study("std3").feature("opt").set("objtable", "new");
    model.study("std3").feature("time").set("disabledphysics", new String[]{"ht/hf1"});
    model.study("std3").setGenPlots(false);
    model.study("std3")
         .label("\u7814\u7a76 3\uff1a\u4f18\u5316\uff08\u4ea5\u59c6\u970d\u5179\u6ee4\u6ce2\u5668\uff09");
    model.study("std3").createAutoSequences("sol");
    model.study("std3").createAutoSequences("jobs");

    model.sol("sol3").runFromTo("st1", "v1");

    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("glob2").set("expr", new String[]{});
    model.result("pg3").feature("glob2").set("descr", new String[]{});
    model.result("pg3").run();
    model.result("pg3").label("\u4ea5\u59c6\u970d\u5179\u6ee4\u6ce2\u5668");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").set("expr", new String[]{"ht.hf2.ntefluxInt"});
    model.result("pg3").feature("glob2").set("descr", new String[]{"\u603b\u51c0\u80fd\u7387"});
    model.result("pg3").feature("glob2").set("unit", new String[]{"W"});
    model.result("pg3").feature("glob2").setIndex("expr", "-ht.hf2.ntefluxInt", 0);
    model.result("pg3").feature("glob2").setIndex("unit", "kW", 0);
    model.result("pg3").feature("glob2").setIndex("descr", "\u529f\u7387", 0);

    model.study("std3").feature("opt").set("plotgroup", "pg3");
    model.study("std3").createAutoSequences("all");
    model.study("std3").feature("opt").set("continuecontrolparams", new String[]{});
    model.study("std3").feature("opt").set("continuecontrolvals", new double[]{});
    model.study("std3").feature("opt").set("continuelagrangevals", new double[]{});
    model.study("std3").feature("opt").set("continuelagrangeparams", new String[]{});

    model.sol("sol3").runAll();

    model.result("pg3").run();

    model.study("std3").feature("opt").set("probewindow", "");

    model.result("pg3").run();

    model.title("\u68d2\u6750\u52a0\u70ed\u7684\u6700\u4f18\u63a7\u5236");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6700\u4f18\u63a7\u5236\u7684\u4f8b\u5b50\uff0c\u5176\u4e2d\u68d2\u6750\u7684\u8f93\u5165\u52a0\u70ed\u529f\u7387\u7ecf\u8fc7\u4f18\u5316\uff0c\u4ece\u800c\u4e3a\u68d2\u6750\u7684\u5916\u90e8\u63d0\u4f9b\u4e00\u5b9a\u7684\u6e29\u5ea6\u3002\u8fd9\u662f\u51fa\u73b0\u6700\u9ad8\u6c34\u6e29\u7684\u4f4d\u7f6e\uff0c\u800c\u6700\u4f4e\u6c34\u6e29\u5219\u51fa\u73b0\u5728\u4e2d\u5fc3\u4f4d\u7f6e\u3002\u56e0\u6b64\uff0c\u8fd9\u4e2a\u95ee\u9898\u5bf9\u5e94\u4e8e\u8fd9\u6837\u4e00\u79cd\u60c5\u51b5\uff1a\u60a8\u5e0c\u671b\u5728\u4e0d\u8d85\u8fc7\u67d0\u4e2a\u6700\u9ad8\u6e29\u5ea6\u7684\u60c5\u51b5\u4e0b\uff0c\u5c3d\u53ef\u80fd\u5feb\u5730\u8fbe\u5230\u67d0\u4e2a\u6700\u4f4e\u6e29\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("optimal_heating_control.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
