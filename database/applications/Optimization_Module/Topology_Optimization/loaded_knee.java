/*
 * loaded_knee.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:47 by COMSOL 6.3.0.290. */
public class loaded_knee {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Topology_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("F_load", "10[kN]");
    model.param().descr("F_load", "\u5916\u52a0\u8f7d\u8377");
    model.param().set("h0", "7.5[mm]");
    model.param().descr("h0", "\u7f51\u683c\u5927\u5c0f");
    model.param().set("WsRef", "1");
    model.param().descr("WsRef", "\u53c2\u8003\u5e94\u53d8\u80fd");
    model.param().set("WsMaxFactor", "1/0.4");
    model.param().descr("WsMaxFactor", "\u6700\u5927\u8bb8\u7528\u76f8\u5bf9\u5e94\u53d8\u80fd");
    model.param().set("d", "2[cm]");
    model.param().descr("d", "\u652f\u67b6\u539a\u5ea6");
    model.param().set("beta", "1");
    model.param().descr("beta", "\u6295\u5f71\u659c\u7387");

    model.component("comp1").geom("geom1").insertFile("loaded_knee_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

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
    model.component("comp1").material().create("matlnk1", "Link");

    model.component("comp1").common().create("dtopo1", "DensityTopology");
    model.component("comp1").common("dtopo1").selection().all();
    model.component("comp1").common("dtopo1").set("projectionType", "TanhProjection");
    model.component("comp1").common("dtopo1").set("beta", "beta");

    model.component("comp1").massProp().create("mass1", "MassProperties");
    model.component("comp1").massProp("mass1").set("densitySource", "fromPhysics");

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", "d");

    model.component("comp1").material().create("toplnk1", "TopologyLink");
    model.component("comp1").material("toplnk1").set("topologySource", "dtopo1");
    model.component("comp1").material("toplnk1").selection().all();

    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(3);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(6);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1").set("force", new String[]{"0", "-F_load", "0"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "h0");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").label("\u62d3\u6251\u4f18\u5316");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg1");
    model.nodeGroup().move("grp1", 1);

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u9608\u503c");

    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");

    model.result().dataset().create("filt1", "Filter");
    model.result().dataset("filt1").label("\u8fc7\u6ee4\u5668");
    model.result().dataset("filt1").set("data", "dset1");
    model.result().dataset("filt1").set("expr", "dtopo1.theta");
    model.result().dataset("filt1").set("lowerexpr", "0.5");
    model.result().dataset("filt1").set("smooth", "none");
    model.result().dataset("filt1").set("useder", false);
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "dtopo1.theta");
    model.result("pg2").feature("surf1").set("rangecoloractive", true);
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("rangecolormin", 0);
    model.result("pg2").feature("surf1").set("rangecolormax", 1);
    model.result("pg3").set("data", "filt1");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").feature("surf1").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u7f5a\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "dtopo1.theta_p");
    model.result("pg2").feature("surf1").set("descr", "\u7f5a\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.result("pg2").feature("surf1").set("colortable", "GrayScale");
    model.result("pg2").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"solid.Ws_tot"});
    model.result().numerical("gev1").set("descr", new String[]{"\u603b\u5f39\u6027\u5e94\u53d8\u80fd"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.param().set("WsRef", "1.3[J]");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "dtopo1.theta_avg");
    model.component("comp1").probe("var1").set("descr", "\u5e73\u5747\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.component("comp1").probe("var1").set("descractive", true);
    model.component("comp1").probe("var1").set("descr", "\u56fa\u4f53\u5206\u6570");
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").set("expr", "solid.Ws_tot");
    model.component("comp1").probe("var2").set("descr", "\u603b\u5f39\u6027\u5e94\u53d8\u80fd");
    model.component("comp1").probe("var2").set("expr", "solid.Ws_tot/WsRef");
    model.component("comp1").probe("var2").set("descractive", true);
    model.component("comp1").probe("var2").set("descr", "\u5e94\u53d8\u80fd\u56e0\u5b50");

    model.study("std1").create("topo", "TopologyOptimization");
    model.study("std1").feature("topo").set("mmamaxiter", 50);
    model.study("std1").feature("topo").set("optobj", new String[]{"comp1.dtopo1.theta_avg"});
    model.study("std1").feature("topo")
         .set("descr", new String[]{"\u5e73\u5747\u6750\u6599\u4f53\u79ef\u56e0\u5b50"});
    model.study("std1").feature("topo").set("constraintExpression", new String[]{"comp1.solid.Ws_tot"});
    model.study("std1").feature("topo").setIndex("constraintExpression", "comp1.solid.Ws_tot/WsRef", 0);
    model.study("std1").feature("topo").setIndex("constraintLbound", "", 0);
    model.study("std1").feature("topo").setIndex("constraintUbound", "", 0);
    model.study("std1").feature("topo").setIndex("constraintUbound", "WsMaxFactor", 0);
    model.study("std1").feature("topo").set("plot", true);
    model.study("std1").feature("topo").set("plotgroup", "pg2");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "F_load", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "N", 0);
    model.study("std1").feature("param").setIndex("pname", "F_load", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "N", 0);
    model.study("std1").feature("param").setIndex("pname", "beta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 8", 0);
    model.study("std1").feature("param").set("probesel", "none");
    model.study("std1").feature("param").set("reusesol", true);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.batch("p1").run("compute");

    model.result("pg1").run();

    model.study("std1").feature("topo").set("probewindow", "");

    model.result("pg2").run();

    model.title("\u8d1f\u8f7d\u819d\u72b6\u7ed3\u6784\u7684\u62d3\u6251\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u56fa\u4f53\u5404\u5411\u540c\u6027\u6750\u6599\u7f5a\u51fd\u6570 (SIMP) \u6a21\u578b\u5f97\u5230\u5f62\u6210 L \u578b\u6846\u67b6\u7684\u7ed9\u5b9a\u6570\u91cf\u6750\u6599\u7684\u6700\u4f73\u5206\u5e03\u3002\u6700\u4f18\u6027\u51c6\u5219\u4e3a\u8f7d\u8377\u5de5\u51b5\u7684\u6700\u5c0f\u67d4\u5ea6\uff0c\u5176\u4e2d\u6846\u67b6\u7684\u4e0a\u8fb9\u754c\u56fa\u5b9a\uff0c\u5e76\u6cbf\u53f3\u4fa7\u8fb9\u754c\u65bd\u52a0\u4e00\u4e2a\u5411\u4e0b\u8f7d\u8377\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("loaded_knee.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
