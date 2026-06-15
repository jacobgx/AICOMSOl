/*
 * beam_eigenfrequency_topology_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:46 by COMSOL 6.3.0.290. */
public class beam_eigenfrequency_topology_optimization {

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

    model.param().set("Lx", "4[m]");
    model.param().descr("Lx", "\u6881\u957f\u5ea6");
    model.param().set("Lyz", "1[m]");
    model.param().descr("Lyz", "\u6881\u5bbd\u5ea6\u548c\u9ad8\u5ea6");
    model.param().set("L1", "0.1[m]");
    model.param().descr("L1", "\u9644\u52a0\u8d28\u91cf\u5bbd\u5ea6");
    model.param().set("thk", "Lyz/10");
    model.param().descr("thk", "\u539a\u5ea6");
    model.param().set("meshsz", "5[cm]");
    model.param().descr("meshsz", "\u7f51\u683c\u5927\u5c0f");
    model.param().set("volfrac", "0.4");
    model.param().descr("volfrac", "\u6700\u5927\u4f53\u79ef\u5206\u6570");
    model.param().set("loadFactor", "0.15");
    model.param().descr("loadFactor", "\u8d28\u91cf\u8f7d\u8377\u56e0\u5b50");
    model.param().set("Emin", "1e-6");
    model.param().descr("Emin", "\u76f8\u5bf9\u6700\u5c0f\u6768\u6c0f\u6a21\u91cf");
    model.param().set("rhoMin", "10*Emin");
    model.param().descr("rhoMin", "\u76f8\u5bf9\u6700\u4f4e\u5bc6\u5ea6");
    model.param().set("pSIMP", "3");
    model.param().descr("pSIMP", "SIMP \u63d2\u503c\u53c2\u6570");
    model.param().set("beta", "1");
    model.param().descr("beta", "\u6295\u5f71\u659c\u7387");
    model.param().set("Rmin", "2*meshsz");
    model.param().descr("Rmin", "\u8fc7\u6ee4\u534a\u5f84");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Lx", "Lyz", "Lyz"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "thk", 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "Lyz/2-2*thk", 1);
    model.component("comp1").geom("geom1").feature("blk1").set("layerfront", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerback", true);
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"Lx-thk", "Lyz-2*thk", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "Lyz-thk", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "thk", "0"});
    model.component("comp1").geom("geom1").feature("blk2").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("blk2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp1").set("quicky", "Lyz/2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").named("blk1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u56fa\u5b9a\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "0.001*Lx");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u8f7d\u8377\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", "Lx-1.001*thk");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", "Lyz/2-thk*1.001");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", "Lyz/2+thk*1.001");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "0.999*Lyz");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u8bbe\u8ba1\u57df");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", "Lyz/1.999");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u955c\u50cf\u57df");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"boxsel3"});

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

    model.component("comp1").common().create("dtopo1", "DensityTopology");
    model.component("comp1").common("dtopo1").selection().all();

    model.component("comp1").geom("geom1").run();

    model.component("comp1").common("dtopo1").selection().named("geom1_boxsel3");
    model.component("comp1").common("dtopo1").set("filterLengthType", "Custom");
    model.component("comp1").common("dtopo1").set("L_min", "Rmin");
    model.component("comp1").common("dtopo1").set("projectionType", "TanhProjection");
    model.component("comp1").common("dtopo1").set("beta", "beta");
    model.component("comp1").common("dtopo1").set("simpExponentType", "Custom");
    model.component("comp1").common("dtopo1").set("p_SIMP", "pSIMP");
    model.component("comp1").common("dtopo1").set("discretization", "constant");
    model.component("comp1").common("dtopo1").set("theta_0", "volfrac");
    model.component("comp1").common().create("mtopo1", "MirrorTopology");
    model.component("comp1").common("mtopo1").selection().named("geom1_comsel1");

    model.component("comp1").material().create("toplnk1", "TopologyLink");
    model.component("comp1").material("toplnk1").set("topologySource", "dtopo1");

    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("solid").create("adm1", "AddedMass2", 2);
    model.component("comp1").physics("solid").feature("adm1").selection().named("geom1_boxsel2");
    model.component("comp1").physics("solid").feature("adm1").set("MassType", "mTot");
    model.component("comp1").physics("solid").feature("adm1")
         .set("mTot", new String[]{"mat1.def.rho*(Lx*Lyz^2-(Lx-thk)*(Lyz-thk)*(Lyz-2*thk))*volfrac*loadFactor", "0", "0", "0", "mat1.def.rho*(Lx*Lyz^2-(Lx-thk)*(Lyz-thk)*(Lyz-2*thk))*volfrac*loadFactor", "0", "0", "0", "mat1.def.rho*(Lx*Lyz^2-(Lx-thk)*(Lyz-thk)*(Lyz-2*thk))*volfrac*loadFactor"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_boxsel1");
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshsz");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "meshsz/2");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stef", "StationaryThenEigenfrequency");
    model.study("std1").create("topo", "TopologyOptimization");
    model.study("std1").feature("topo").set("mmamaxiter", 50);
    model.study("std1").feature("topo").set("movelimitactive", true);
    model.study("std1").feature("topo").setIndex("optobj", "freq", 0);
    model.study("std1").feature("topo").set("objectivetype", "maximization");
    model.study("std1").feature("topo").set("objectivesolution", "min");
    model.study("std1").feature("topo").set("objectivescaling", "init");
    model.study("std1").feature("topo").set("constraintExpression", new String[]{"comp1.dtopo1.theta_avg"});
    model.study("std1").feature("topo").setIndex("constraintUbound", "volfrac", 0);
    model.study("std1").feature("topo").setIndex("constraintLbound", "", 0);
    model.study("std1").label("\u7814\u7a76 1 - \u4f18\u5316");
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

    model.sol("sol1").feature("o1").set("gcmma", false);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Lx", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Lx", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Lyz", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "Lyz", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "pSIMP", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 2 3 4 5", 0);
    model.study("std1").feature("param").setIndex("pname", "beta", 1);
    model.study("std1").feature("param").setIndex("plistarr", "2 4 8 16 32", 1);
    model.study("std1").feature("param").set("reusesol", true);
    model.study("std1").feature("topo").set("plot", true);
    model.study("std1").feature("topo").set("plotgroup", "pg3");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").setIndex("looplevel", 5, 1);
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
    model.nodeGroup("grp2").label("\u62d3\u6251\u4f18\u5316 1");
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
    model.result().dataset("filt2").set("data", "dset2");
    model.result().dataset("filt2").set("expr", "dtopo1.theta");
    model.result().dataset("filt2").set("lowerexpr", "0.5");
    model.result().dataset("filt2").set("smooth", "none");
    model.result().dataset("filt2").set("useder", false);
    model.result("pg5").set("data", "dset2");
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

    model.study("std1").feature("topo").set("probewindow", "");

    model.result("pg3").run();
    model.result("pg3").run();

    model.nodeGroup("grp2").label("\u62d3\u6251\u4f18\u5316 - \u53c2\u6570\u5316");

    model.component("comp1").mesh().duplicate("mesh2", "mesh1");
    model.component("comp1").mesh("mesh2").feature("size").set("hmax", "meshsz/2");
    model.component("comp1").mesh("mesh2").feature("size").set("hmin", "meshsz/4");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").setGenPlots(false);
    model.study("std2").label("\u7814\u7a76 2 - \u5e73\u6ed1\u8bbe\u8ba1 (mesh2)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol8").runAll();

    model.result().dataset("filt1").set("data", "dset3");
    model.result().dataset("filt1").set("expr", "dtopo1.theta_f");
    model.result().dataset("filt1").createMesh("comp2", "geom2", "mesh3", "imp1");

    model.component("comp2").mesh("mesh3").feature("imp1").set("facepartition", "minimal");
    model.component("comp2").mesh("mesh3").feature().duplicate("imp2", "imp1");
    model.component("comp2").mesh("mesh3").run("imp1");
    model.component("comp2").mesh("mesh3").feature("imp2").create("tr1", "Transform");
    model.component("comp2").mesh("mesh3").feature("imp2").feature("tr1")
         .set("displ", new String[]{"0", "Lyz", "0"});
    model.component("comp2").mesh("mesh3").feature("imp2").feature("tr1").set("scaletype", "anisotropic");
    model.component("comp2").mesh("mesh3").feature("imp2").feature("tr1").set("anisotropic", new int[]{1, -1, 1});
    model.component("comp2").mesh("mesh3").run("imp2");
    model.component("comp2").mesh("mesh3").create("uni1", "Union");
    model.component("comp2").mesh("mesh3").run("uni1");
    model.component("comp2").mesh("mesh3").create("remf1", "RemeshFaces");
    model.component("comp2").mesh("mesh3").feature("remf1").selection().all();
    model.component("comp2").mesh("mesh3").feature("remf1").feature("size").set("hmax", "meshsz");
    model.component("comp2").mesh("mesh3").feature("remf1").feature("size").set("hmin", "meshsz/2");
    model.component("comp2").mesh("mesh3").run("remf1");
    model.component("comp2").mesh("mesh3").create("ftet1", "FreeTet");
    model.component("comp2").mesh("mesh3").run();

    model.component("comp2").material().create("matlnk1", "Link");

    model.component("comp2").physics().copy("solid2", "solid");
    model.component("comp2").physics("solid2").feature("fix1").selection().named("mesh3_imp1_geom1_boxsel1");
    model.component("comp2").physics("solid2").feature().duplicate("fix2", "fix1");
    model.component("comp2").physics("solid2").feature("fix2").selection().named("mesh3_imp2_geom1_boxsel1");
    model.component("comp2").physics("solid2").feature("adm1").selection().named("mesh3_imp1_geom1_boxsel2");
    model.component("comp2").physics("solid2").feature("adm1")
         .set("mTot", new String[]{"0.5*mat1.def.rho*(Lx*Lyz^2-(Lx-thk)*(Lyz-thk)*(Lyz-2*thk))*volfrac*loadFactor", "0", "0", "0", "0.5*mat1.def.rho*(Lx*Lyz^2-(Lx-thk)*(Lyz-thk)*(Lyz-2*thk))*volfrac*loadFactor", "0", "0", "0", "0.5*mat1.def.rho*(Lx*Lyz^2-(Lx-thk)*(Lyz-thk)*(Lyz-2*thk))*volfrac*loadFactor"});
    model.component("comp2").physics("solid2").feature().duplicate("adm2", "adm1");
    model.component("comp2").physics("solid2").feature("adm2").selection().named("mesh3_imp2_geom1_boxsel2");

    model.study().create("std3");
    model.study("std3").create("eig", "Eigenfrequency");
    model.study("std3").feature("eig").set("plotgroup", "Default");
    model.study("std3").feature("eig").set("storefact", false);
    model.study("std3").feature("eig").set("solnum", "auto");
    model.study("std3").feature("eig").set("notsolnum", "auto");
    model.study("std3").feature("eig").set("outputmap", new String[]{});
    model.study("std3").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").setSolveFor("/physics/solid", false);
    model.study("std3").feature("eig").setSolveFor("/physics/solid2", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");
    model.component("comp2").common().create("mpf2", "ParticipationFactors");

    model.study("std1").feature("stef").setSolveFor("/physics/solid2", false);
    model.study("std2").feature("stat").setSolveFor("/physics/solid2", false);
    model.study("std3").feature("eig").setSolveFor("/common/dtopo1", false);
    model.study("std3").label("\u7814\u7a76 3 - \u9a8c\u8bc1");
    model.study("std3").createAutoSequences("all");

    model.sol("sol9").runAll();

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset5");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").label("\u632f\u578b (solid2)");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"solid2.disp"});
    model.result("pg7").feature("surf1").set("threshold", "manual");
    model.result("pg7").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").feature("surf1").set("colortabletrans", "none");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg7").feature("surf1").create("def", "Deform");
    model.result("pg7").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std3EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std3EvgFrq").set("data", "dset5");
    model.result().evaluationGroup("std3EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 3 - \u9a8c\u8bc1)");
    model.result().evaluationGroup("std3EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std3EvgFrq").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").label("\u62d3\u6251\u4f18\u5316 1");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup("grp3").placeAfter("plotgroup", "pg7");
    model.nodeGroup().move("grp3", 3);

    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50 2");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u9608\u503c 2");

    model.nodeGroup("grp3").add("plotgroup", "pg8");
    model.nodeGroup("grp3").add("plotgroup", "pg9");

    model.result().dataset().create("filt3", "Filter");
    model.result().dataset("filt3").label("\u8fc7\u6ee4\u5668 2");
    model.result().dataset("filt3").set("data", "dset4");
    model.result().dataset("filt3").set("expr", "dtopo1.theta");
    model.result().dataset("filt3").set("lowerexpr", "0.5");
    model.result().dataset("filt3").set("smooth", "none");
    model.result().dataset("filt3").set("useder", false);
    model.result("pg8").set("data", "dset4");
    model.result("pg8").create("mslc1", "Multislice");
    model.result("pg8").feature("mslc1").set("expr", "dtopo1.theta");
    model.result("pg8").feature("mslc1").set("rangecoloractive", true);
    model.result("pg8").feature("mslc1").set("rangecolormin", 0);
    model.result("pg8").feature("mslc1").set("rangecolormax", 1);
    model.result("pg8").feature("mslc1").set("xnumber", "1");
    model.result("pg8").feature("mslc1").set("ynumber", "1");
    model.result("pg8").feature("mslc1").set("znumber", "1");
    model.result("pg9").set("data", "filt3");
    model.result("pg9").create("vol1", "Volume");
    model.result("pg9").feature("vol1").set("expr", "1");
    model.result("pg9").feature("vol1").set("coloring", "uniform");
    model.result("pg9").feature("vol1").set("color", "gray");
    model.result("pg9").feature("vol1").set("titletype", "none");
    model.result().export().create("plot5", "Plot");
    model.result().export("plot5").set("plotgroup", "pg9");
    model.result().export("plot5").label("\u62d3\u6251\u4f18\u5316\u7684\u51e0\u4f55 (STL) 2");
    model.result().export("plot5").set("exporttype", "stlbin");
    model.result().export("plot5").set("filename", "comp1_dtopo1.stl");
    model.result().export().create("plot6", "Plot");
    model.result().export("plot6").set("plotgroup", "pg9");
    model.result().export("plot6").label("\u62d3\u6251\u4f18\u5316\u7684\u51e0\u4f55 (PLY) 2");
    model.result().export("plot6").set("exporttype", "plybin");
    model.result().export("plot6").set("filename", "comp1_dtopo1.ply");
    model.result().evaluationGroup().create("std3mpf2", "EvaluationGroup");
    model.result().evaluationGroup("std3mpf2").set("data", "dset5");
    model.result().evaluationGroup("std3mpf2").label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 3 - \u9a8c\u8bc1)");
    model.result().evaluationGroup("std3mpf2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormX", 0);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormY", 1);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormZ", 2);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormX", 3);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormY", 4);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormZ", 5);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLX", 6);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLY", 7);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLZ", 8);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRX", 9);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRY", 10);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRZ", 11);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std3mpf2").run();
    model.result("pg7").run();

    model.nodeGroup().remove("grp3");

    model.result("pg7").run();
    model.result("pg7").label("\u9a8c\u8bc1");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u9a8c\u8bc1");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"dtopo1.theta_avg"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u5e73\u5747\u6750\u6599\u4f53\u79ef\u56e0\u5b50"});
    model.result().evaluationGroup("eg1").create("int1", "IntVolume");
    model.result().evaluationGroup("eg1").feature("int1").set("data", "dset5");
    model.result().evaluationGroup("eg1").feature("int1").selection().all();
    model.result().evaluationGroup("eg1").feature("int1")
         .setIndex("expr", "1/(Lx*Lyz^2-(Lx-thk)*(Lyz-thk)*(Lyz-2*thk))", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "\u4f53\u79ef\u5206\u6570", 0);
    model.result().evaluationGroup("eg1").run();

    model.title("\u6881\u7684\u7279\u5f81\u9891\u7387\u6700\u5927\u5316");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u5c06\u201c\u62d3\u6251\u4f18\u5316\u201d\u63a5\u53e3\u4e2d\u7684\u201c\u5bc6\u5ea6\u6a21\u578b\u201d\u7279\u5f81\u4e0e\u201c\u5148\u7a33\u6001\uff0c\u540e\u7279\u5f81\u9891\u7387\u201d\u7814\u7a76\u6b65\u9aa4\u76f8\u7ed3\u5408\uff0c\u4ee5\u8bbe\u8ba1\u6ca1\u6709\u4f4e\u7279\u5f81\u9891\u7387\u7684\u6881\u3002");

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

    model.label("beam_eigenfrequency_topology_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
