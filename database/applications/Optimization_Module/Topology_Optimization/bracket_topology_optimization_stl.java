/*
 * bracket_topology_optimization_stl.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:46 by COMSOL 6.3.0.290. */
public class bracket_topology_optimization_stl {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Topology_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").create("topo", "TopologyOptimization");
    model.study("std1").feature("topo").setSolveFor("/physics/solid", true);

    model.component("comp1").common().create("dtopo1", "DensityTopology");
    model.component("comp1").common("dtopo1").selection().all();

    model.component("comp1").material().create("toplnk1", "TopologyLink");
    model.component("comp1").material("toplnk1").set("topologySource", "dtopo1");
    model.component("comp1").material("toplnk1").selection().all();

    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);

    model.study("std1").feature("topo").setIndex("optobj", "comp1.solid.Ws_tot", 0);
    model.study("std1").feature("topo").set("objectivescaling", "init");
    model.study("std1").feature("topo").setIndex("constraintExpression", "comp1.dtopo1.theta_avg", 0);
    model.study("std1").feature("topo").setIndex("constraintUbound", "0.5", 0);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("D1", "1.4[cm]", "\u7ea6\u675f\u76f4\u5f84");
    model.param().set("D2", "5[cm]", "\u8f7d\u8377\u76f4\u5f84");
    model.param().set("H", "10[cm]", "\u9ad8\u5ea6");
    model.param().set("W", "21.5[cm]", "\u5bbd\u5ea6");
    model.param().set("L", "35[cm]", "\u957f\u5ea6");
    model.param().set("thickness", "8[mm]", "\u652f\u67b6\u539a\u5ea6");
    model.param().set("rfillet", "8[mm]", "\u5706\u89d2\u534a\u5f84");
    model.param().set("Y1", "2[cm]", "\u7ea6\u675f\u5b54\u7684\u4f4d\u7f6e");
    model.param().set("X1", "4[cm]", "\u7ea6\u675f\u5b54\u7684\u4f4d\u7f6e");
    model.param().set("DY1", "6[cm]", "\u7ea6\u675f\u5b54\u7684\u95f4\u8ddd");
    model.param().set("YC", "-30[cm]", "\u52a0\u8f7d\u5b54\u4f4d\u7f6e");
    model.param().set("L1", "10[cm]", "\u8fde\u63a5\u677f\u5bbd\u5ea6");
    model.param().set("rfillet2", "2[cm]", "\u7ea6\u675f\u677f\u5706\u89d2\u534a\u5f84");
    model.param().set("W1", "7[cm]", "\u7ea6\u675f\u677f\u5bbd\u5ea6");
    model.param().set("Da", "1[cm]", "\u56fa\u5b9a\u5b54\u586b\u5145");

    model.component("comp1").geom("geom1")
         .insertFile("bracket_topology_optimization_stl_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("boxsel4");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").set("Lmin", "5[mm]");
    model.param("par2").descr("Lmin", "\u8fc7\u6ee4\u534a\u5f84");
    model.param("par2").set("meshsz", "Lmin");
    model.param("par2").descr("meshsz", "\u7f51\u683c\u5927\u5c0f");
    model.param("par2").set("meshsz2", "Lmin/2");
    model.param("par2").descr("meshsz2", "\u7ec6\u5316\u7f51\u683c\u5927\u5c0f");
    model.param("par2").set("P0", "2.5[MPa]");
    model.param("par2").descr("P0", "\u5cf0\u503c\u8f7d\u8377\u5f3a\u5ea6");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "load");
    model.component("comp1").func("an1").set("expr", "F*cos(atan2(py,abs(px)))");
    model.component("comp1").func("an1").set("args", "F,px,py");
    model.component("comp1").func("an1").setIndex("argunit", "Pa", 0);
    model.component("comp1").func("an1").setIndex("argunit", "m", 1);
    model.component("comp1").func("an1").setIndex("argunit", "m", 2);
    model.component("comp1").func("an1").set("fununit", "Pa");

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
    model.component("comp1").material("toplnk1").set("link", "mat1");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("geom1_cylsel1");

    model.group().create("cg1", "ConstraintGroup");

    model.component("comp1").physics("solid").feature("fix1").set("constraintGroup", "cg1");
    model.component("comp1").physics("solid").create("fix2", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix2").selection().named("geom1_cylsel2");

    model.group().create("cg2", "ConstraintGroup");

    model.component("comp1").physics("solid").feature("fix2").set("constraintGroup", "cg2");
    model.component("comp1").physics("solid").create("fix3", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix3").selection().named("geom1_cylsel3");

    model.group().create("cg3", "ConstraintGroup");

    model.component("comp1").physics("solid").feature("fix3").set("constraintGroup", "cg3");
    model.component("comp1").physics("solid").create("fix4", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix4").selection().named("geom1_cylsel4");

    model.group().create("cg4", "ConstraintGroup");

    model.component("comp1").physics("solid").feature("fix4").set("constraintGroup", "cg4");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("geom1_unisel1");
    model.component("comp1").physics("solid").feature("bndl1").set("coordinateSystem", "sys1");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "load(-P0,Y-YC,Z)"});

    model.group().create("lg1", "LoadGroup");

    model.component("comp1").physics("solid").feature("bndl1").set("loadGroup", "lg1");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 4, 33, 37, 43, 47);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshsz");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "meshsz/2");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_difsel2");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("geom1_boxsel3");
    model.component("comp1").mesh("mesh1").create("cpd1", "CopyDomain");
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source").geom(3);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination").geom(3);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source").named("geom1_boxsel1");
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination").named("geom1_boxsel2");

    model.param("par2").set("Vfrac", "0.5");
    model.param("par2").descr("Vfrac", "\u4f53\u79ef\u5206\u6570");
    model.param("par2").set("simpP", "1");
    model.param("par2").descr("simpP", "SIMP \u6307\u6570");
    model.param("par2").set("proj_beta", "1");
    model.param("par2").descr("proj_beta", "\u6295\u5f71\u659c\u7387");

    model.component("comp1").common("dtopo1").selection().named("geom1_difsel1");
    model.component("comp1").common("dtopo1").set("filterLengthType", "Custom");
    model.component("comp1").common("dtopo1").set("L_min", "meshsz");
    model.component("comp1").common("dtopo1").set("projectionType", "TanhProjection");
    model.component("comp1").common("dtopo1").set("beta", "proj_beta");
    model.component("comp1").common("dtopo1").set("simpExponentType", "Custom");
    model.component("comp1").common("dtopo1").set("p_SIMP", "simpP");
    model.component("comp1").common("dtopo1").set("discretization", "constant");
    model.component("comp1").common("dtopo1").set("theta_0", "Vfrac");
    model.component("comp1").common().create("ftopom1", "MaterialTopologyDomain");
    model.component("comp1").common("ftopom1").selection().named("geom1_adjsel1");
    model.component("comp1").common().create("ftopobm1", "MaterialTopologyBoundary");
    model.component("comp1").common("ftopobm1").selection().named("geom1_cylsel9");
    model.component("comp1").common().create("mtopo1", "MirrorTopology");
    model.component("comp1").common("mtopo1").selection().named("geom1_boxsel4");

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").setSolveFor("/common/mtopo1", false);
    model.study("std1").feature("stat").set("disabledcommon", new String[]{"mtopo1"});
    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 2);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 3);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 2);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 3);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", true, 0, 1);
    model.study("std1").feature("stat").setIndex("constraintgroup", true, 0, 2);
    model.study("std1").feature("stat").setIndex("constraintgroup", true, 0, 3);
    model.study("std1").feature("topo").set("mmamaxiter", 50);
    model.study("std1").feature("topo").setIndex("constraintUbound", "Vfrac", 0);
    model.study("std1").feature("topo").setIndex("constraintLbound", "", 0);
    model.study("std1")
         .label("\u7814\u7a76 1\uff1a\u4e00\u4e2a\u8f7d\u8377\u5de5\u51b5\uff0c\u65e0\u5bf9\u79f0\u6027");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").label("\u62d3\u6251\u4f18\u5316");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg1");
    model.nodeGroup().move("grp1", 1);

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.result().create("pg3", "PlotGroup3D");
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
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "dtopo1.theta");
    model.result("pg2").feature("mslc1").set("rangecoloractive", true);
    model.result("pg2").feature("mslc1").set("rangecolormin", 0);
    model.result("pg2").feature("mslc1").set("rangecolormax", 1);
    model.result("pg2").feature("mslc1").set("xnumber", "1");
    model.result("pg2").feature("mslc1").set("ynumber", "1");
    model.result("pg2").feature("mslc1").set("znumber", "1");
    model.result("pg3").set("data", "filt1");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", "1");
    model.result("pg3").feature("vol1").set("coloring", "uniform");
    model.result("pg3").feature("vol1").set("color", "gray");
    model.result("pg3").feature("vol1").set("titletype", "none");
    model.result().export().create("plot1", "Plot");
    model.result().export("plot1").set("plotgroup", "pg3");
    model.result().export("plot1").label("\u62d3\u6251\u4f18\u5316\u7684\u51e0\u4f55 (STL)");
    model.result().export("plot1").set("exporttype", "stlbin");
    model.result().export("plot1").set("filename", "comp1_dtopo1.stl");
    model.result().export().create("plot2", "Plot");
    model.result().export("plot2").set("plotgroup", "pg3");
    model.result().export("plot2").label("\u62d3\u6251\u4f18\u5316\u7684\u51e0\u4f55 (PLY)");
    model.result().export("plot2").set("exporttype", "plybin");
    model.result().export("plot2").set("filename", "comp1_dtopo1.ply");
    model.result("pg1").run();

    model.study("std1").feature("topo").set("plot", true);
    model.study("std1").feature("topo").set("plotgroup", "pg3");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "D1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "D1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "D2", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "D2", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "simpP", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 2 3 4", 0);
    model.study("std1").feature("param").setIndex("pname", "proj_beta", 1);
    model.study("std1").feature("param").setIndex("plistarr", "2 4 6 8", 1);
    model.study("std1").feature("param").set("reusesol", true);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result("pg1").run();

    model.study("std1").feature("topo").set("probewindow", "");

    model.nodeGroup("grp1").label("\u4e00\u4e2a\u8f7d\u8377\u5de5\u51b5");

    model.result("pg1").run();

    model.nodeGroup("grp1").add("plotgroup", "pg1");

    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp1").physics("solid").feature().duplicate("bndl2", "bndl1");
    model.component("comp1").physics("solid").feature("bndl2").selection().named("geom1_unisel2");

    model.group().create("lg2", "LoadGroup");

    model.component("comp1").physics("solid").feature("bndl2").set("loadGroup", "lg2");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").set("useloadcase", true);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 0, 0);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 0, 1);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 0, 2);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 0, 3);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 0, 0);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 0, 1);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 0, 2);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 0, 3);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 1, 0);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 1, 1);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 1, 2);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 1, 3);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 1, 0);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 1, 1);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 1, 2);
    model.study("std2").feature("stat").setIndex("constraintgroup", false, 1, 3);
    model.study("std2").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std2").feature("stat").setIndex("constraintgroup", true, 0, 1);
    model.study("std2").feature("stat").setIndex("constraintgroup", true, 0, 2);
    model.study("std2").feature("stat").setIndex("constraintgroup", true, 0, 3);
    model.study("std2").feature("stat").setIndex("loadgroup", true, 1, 1);
    model.study("std2").feature("stat").setIndex("constraintgroup", true, 1, 0);
    model.study("std2").feature("stat").setIndex("constraintgroup", true, 1, 2);
    model.study("std2").feature("stat").setIndex("constraintgroup", true, 1, 3);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u4e24\u4e2a\u8f7d\u8377\u5de5\u51b5");
    model.study("std2").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").label("\u5e94\u529b (solid) 1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("vol1").set("threshold", "manual");
    model.result("pg4").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("vol1").set("colortable", "Rainbow");
    model.result("pg4").feature("vol1").set("colortabletrans", "none");
    model.result("pg4").feature("vol1").set("colorscalemode", "linear");
    model.result("pg4").feature("vol1").set("resolution", "custom");
    model.result("pg4").feature("vol1").set("refine", 2);
    model.result("pg4").feature("vol1").set("colortable", "Prism");
    model.result("pg4").feature("vol1").create("def", "Deform");
    model.result("pg4").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").label("\u62d3\u6251\u4f18\u5316");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg4");
    model.nodeGroup().move("grp2", 2);

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50 1");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u9608\u503c 1");

    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");

    model.result().dataset().create("filt2", "Filter");
    model.result().dataset("filt2").label("\u8fc7\u6ee4\u5668 1");
    model.result().dataset("filt2").set("data", "dset3");
    model.result().dataset("filt2").set("expr", "dtopo1.theta");
    model.result().dataset("filt2").set("lowerexpr", "0.5");
    model.result().dataset("filt2").set("smooth", "none");
    model.result().dataset("filt2").set("useder", false);
    model.result("pg5").set("data", "dset3");
    model.result("pg5").create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("expr", "dtopo1.theta");
    model.result("pg5").feature("mslc1").set("rangecoloractive", true);
    model.result("pg5").feature("mslc1").set("rangecolormin", 0);
    model.result("pg5").feature("mslc1").set("rangecolormax", 1);
    model.result("pg5").feature("mslc1").set("xnumber", "1");
    model.result("pg5").feature("mslc1").set("ynumber", "1");
    model.result("pg5").feature("mslc1").set("znumber", "1");
    model.result("pg6").set("data", "filt2");
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("expr", "1");
    model.result("pg6").feature("vol1").set("coloring", "uniform");
    model.result("pg6").feature("vol1").set("color", "gray");
    model.result("pg6").feature("vol1").set("titletype", "none");
    model.result().export().create("plot3", "Plot");
    model.result().export("plot3").set("plotgroup", "pg6");
    model.result().export("plot3").label("\u62d3\u6251\u4f18\u5316\u7684\u51e0\u4f55 (STL) 1");
    model.result().export("plot3").set("exporttype", "stlbin");
    model.result().export("plot3").set("filename", "comp1_dtopo1.stl");
    model.result().export().create("plot4", "Plot");
    model.result().export("plot4").set("plotgroup", "pg6");
    model.result().export("plot4").label("\u62d3\u6251\u4f18\u5316\u7684\u51e0\u4f55 (PLY) 1");
    model.result().export("plot4").set("exporttype", "plybin");
    model.result().export("plot4").set("filename", "comp1_dtopo1.ply");
    model.result("pg4").run();

    model.study("std2").feature().copy("param", "std1/param");
    model.study("std2").feature().copy("topo", "std1/topo");
    model.study("std2").feature("topo").setIndex("optobj", "comp1.solid.Ws_tot/4", 0);
    model.study("std2").feature("topo").setIndex("descr", "", 0);
    model.study("std2").feature("topo").set("plotgroup", "pg6");
    model.study("std2").setGenPlots(false);

    model.sol("sol7").createAutoSequence("std2");
    model.sol("sol7").feature("o1").feature("s1").feature("se1").feature("ss2").set("linsolver", "i2");
    model.sol("sol7").feature("o1").feature("s1").feature("se1").set("segterm", "iter");
    model.sol("sol7").feature("o1").feature("s1").feature("aDef").set("cachepattern", false);
    model.sol("sol7").feature("o1").feature("s1").feature("pDef").set("plooporder", "manual");

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol8");
    model.sol("sol8").study("std2");
    model.sol("sol8").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol8");
    model.batch("p2").run("compute");

    model.result("pg4").run();

    model.study("std2").feature("topo").set("probewindow", "");

    model.nodeGroup("grp2").label("\u4e24\u4e2a\u8f7d\u8377\u5de5\u51b5");

    model.result("pg4").run();

    model.nodeGroup("grp2").add("plotgroup", "pg4");

    model.result("pg6").run();
    model.result("pg6").run();

    model.component("comp1").mesh().duplicate("mesh2", "mesh1");
    model.component("comp1").mesh("mesh2").feature("size").set("hmax", "meshsz/2");
    model.component("comp1").mesh("mesh2").feature("size").set("hmin", "meshsz/4");
    model.component("comp1").mesh("mesh2").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u5e73\u6ed1\u8bbe\u8ba1\uff08mesh2\uff09");
    model.study("std3").showAutoSequences("all");
    model.study("std3").feature("stat").set("useinitsol", true);
    model.study("std3").feature("stat").set("initmethod", "sol");
    model.study("std3").feature("stat").set("initstudy", "std2");
    model.study("std3").createAutoSequences("all");

    model.sol("sol13").runAll();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").label("\u62d3\u6251\u4f18\u5316");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 3);

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50 2");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u9608\u503c 2");

    model.nodeGroup("grp3").add("plotgroup", "pg7");
    model.nodeGroup("grp3").add("plotgroup", "pg8");

    model.result().dataset().create("filt3", "Filter");
    model.result().dataset("filt3").label("\u8fc7\u6ee4\u5668 2");
    model.result().dataset("filt3").set("data", "dset5");
    model.result().dataset("filt3").set("expr", "dtopo1.theta");
    model.result().dataset("filt3").set("lowerexpr", "0.5");
    model.result().dataset("filt3").set("smooth", "none");
    model.result().dataset("filt3").set("useder", false);
    model.result("pg7").set("data", "dset5");
    model.result("pg7").create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").set("expr", "dtopo1.theta");
    model.result("pg7").feature("mslc1").set("rangecoloractive", true);
    model.result("pg7").feature("mslc1").set("rangecolormin", 0);
    model.result("pg7").feature("mslc1").set("rangecolormax", 1);
    model.result("pg7").feature("mslc1").set("xnumber", "1");
    model.result("pg7").feature("mslc1").set("ynumber", "1");
    model.result("pg7").feature("mslc1").set("znumber", "1");
    model.result("pg8").set("data", "filt3");
    model.result("pg8").create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("expr", "1");
    model.result("pg8").feature("vol1").set("coloring", "uniform");
    model.result("pg8").feature("vol1").set("color", "gray");
    model.result("pg8").feature("vol1").set("titletype", "none");
    model.result().export().create("plot5", "Plot");
    model.result().export("plot5").set("plotgroup", "pg8");
    model.result().export("plot5").label("\u62d3\u6251\u4f18\u5316\u7684\u51e0\u4f55 (STL) 2");
    model.result().export("plot5").set("exporttype", "stlbin");
    model.result().export("plot5").set("filename", "comp1_dtopo1.stl");
    model.result().export().create("plot6", "Plot");
    model.result().export("plot6").set("plotgroup", "pg8");
    model.result().export("plot6").label("\u62d3\u6251\u4f18\u5316\u7684\u51e0\u4f55 (PLY) 2");
    model.result().export("plot6").set("exporttype", "plybin");
    model.result().export("plot6").set("filename", "comp1_dtopo1.ply");
    model.result("pg7").run();
    model.result().dataset("filt3").createMesh("comp2", "geom2", "mesh3", "imp1");

    model.component("comp2").mesh("mesh3").feature("imp1").set("facepartition", "minimal");
    model.component("comp2").mesh("mesh3").feature("imp1").importData();
    model.component("comp2").mesh("mesh3").create("remf1", "RemeshFaces");
    model.component("comp2").mesh("mesh3").feature("remf1").selection().all();
    model.component("comp2").mesh("mesh3").feature("remf1").feature("size").set("hmax", "meshsz");
    model.component("comp2").mesh("mesh3").feature("remf1").feature("size").set("hmin", "meshsz/2");
    model.component("comp2").mesh("mesh3").feature("remf1").feature("size").set("hcurve", 1);
    model.component("comp2").mesh("mesh3").feature("remf1").feature("size").set("hnarrow", 0);
    model.component("comp2").mesh("mesh3").run("remf1");
    model.component("comp2").mesh("mesh3").create("ftet1", "FreeTet");
    model.component("comp2").mesh("mesh3").feature("ftet1").feature("size").set("hauto", 1);
    model.component("comp2").mesh("mesh3").run();

    model.component("comp2").material().create("matlnk1", "Link");

    model.component("comp2").physics().copy("solid2", "solid");
    model.component("comp2").physics("solid2").prop("ShapeProperty").set("order_displacement", "2s");
    model.component("comp2").physics("solid2").feature("fix1").selection().named("mesh3_imp1_geom1_cylsel1");
    model.component("comp2").physics("solid2").feature("fix2").selection().named("mesh3_imp1_geom1_cylsel2");
    model.component("comp2").physics("solid2").feature("fix3").selection().named("mesh3_imp1_geom1_cylsel3");
    model.component("comp2").physics("solid2").feature("fix4").selection().named("mesh3_imp1_geom1_cylsel4");
    model.component("comp2").physics("solid2").feature("bndl1").selection().named("mesh3_imp1_geom1_unisel1");
    model.component("comp2").physics("solid2").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "comp1.load(-P0,Y-YC,Z)"});
    model.component("comp2").physics("solid2").feature("bndl2").selection().named("mesh3_imp1_geom1_unisel2");
    model.component("comp2").physics("solid2").feature("bndl2")
         .set("forceReferenceArea", new String[]{"0", "0", "comp1.load(-P0,Y-YC,Z)"});

    model.study().create("std4");
    model.study("std4").label("\u7814\u7a76 4\uff1a\u9a8c\u8bc1");
    model.study("std4").setGenPlots(false);
    model.study("std1").feature("stat").setSolveFor("/physics/solid2", false);
    model.study("std3").feature("stat").setSolveFor("/physics/solid2", false);
    model.study("std2").feature("stat").setSolveFor("/physics/solid2", false);
    model.study("std4").feature().copy("stat", "std2/stat");
    model.study("std4").feature("stat").setSolveFor("/physics/solid2", true);
    model.study("std4").feature("stat").setSolveFor("/common/dtopo1", false);
    model.study("std4").createAutoSequences("all");

    model.sol("sol14").runAll();

    model.nodeGroup().remove("grp3");

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u4f4d\u79fb (solid2)");
    model.result("pg7").set("data", "dset7");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").create("def1", "Deform");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").run();

    model.title("\u652f\u67b6 - \u62d3\u6251\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u591a\u4e2a\u8f7d\u8377\u548c\u7ea6\u675f\u5de5\u51b5\u6f14\u793a\u5982\u4f55\u6267\u884c\u62d3\u6251\u4f18\u5316\u3002\u62d3\u6251\u4f18\u5316\u7684\u5bc6\u5ea6\u7279\u5f81\u4e0e\u53c2\u6570\u5316\u626b\u63cf\u7ed3\u5408\u4f7f\u7528\uff0c\u5176\u4e2d\u6bcf\u4e2a\u4f18\u5316\u7684\u89e3\u90fd\u7528\u4f5c\u8fdb\u4e00\u6b65\u6539\u8fdb\u4f18\u5316\u7684\u8f93\u5165\u3002\u901a\u8fc7\u5bfc\u51fa\u6700\u7ec8\u4f18\u5316\u7684\u51e0\u4f55\u5e76\u5c06\u5176\u5bfc\u5165\u6765\u8fdb\u884c\u9a8c\u8bc1\u5206\u6790\uff08\u6392\u9664\u5f53\u524d\u7a7a\u533a\u57df\uff09\u3002");

    model.component("comp1").mesh("mesh1").clearMesh();
    model.component("comp1").mesh("mesh2").clearMesh();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();

    model.label("bracket_topology_optimization_stl.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
