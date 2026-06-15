/*
 * roller_conveyor_dynamics.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:25 by COMSOL 6.3.0.290. */
public class roller_conveyor_dynamics {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("fp", "1/100000", "\u7f5a\u56e0\u5b50\u4e58\u5b50");
    model.param().set("mu", "0.1", "\u6469\u64e6\u7cfb\u6570");
    model.param().set("rpm", "765", "\u8f8a\u5b50\u6bcf\u5206\u949f\u8f6c\u6570");
    model.param().set("omega", "2*pi[rad]*rpm/60[s]", "\u8f8a\u5b50\u89d2\u901f\u5ea6");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "roller_conveyor_dynamics.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("imp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("imp1").set("selindividual", true);
    model.component("comp1").geom("geom1").feature("imp1").set("selindividualshow", "all");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u7403");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("imp1(14)", 1);
    model.component("comp1").geom("geom1").feature("sel1").set("color", "custom");
    model.component("comp1").geom("geom1").feature("sel1")
         .set("customcolor", new double[]{0.9686274528503418, 0.8352941274642944, 0.6431372761726379});
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u7403\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u6846\u67b6");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("imp1(1)", 1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("imp1(23)", 1);
    model.component("comp1").geom("geom1").feature("sel2").set("color", "custom");
    model.component("comp1").geom("geom1").feature("sel2")
         .set("customcolor", new double[]{0.3921568691730499, 0.3921568691730499, 0.501960813999176});
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u5bfc\u8f68");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection")
         .set(new String[]{"imp1(17)", "imp1(2)", "imp1(22)", "imp1(25)", "imp1(26)", "imp1(29)", "imp1(3)", "imp1(33)"}, new int[][]{{1}, {1, 2}, {1}, {1}, {1}, {1}, {1}, {1, 2}});
    model.component("comp1").geom("geom1").feature("sel3").set("color", "custom");
    model.component("comp1").geom("geom1").feature("sel3")
         .set("customcolor", new double[]{0.6980392336845398, 0.729411780834198, 0.364705890417099});
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u6258\u76d8");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("imp1(42)", 1, 2);
    model.component("comp1").geom("geom1").feature("sel4").set("color", "custom");
    model.component("comp1").geom("geom1").feature("sel4")
         .set("customcolor", new double[]{0.5843137502670288, 0.7843137383460999, 0.7843137383460999});
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u8f8a\u5b50");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"imp1"});
    model.component("comp1").geom("geom1").feature("difsel1")
         .set("subtract", new String[]{"sel1", "sel2", "sel3", "sel4"});
    model.component("comp1").geom("geom1").feature("difsel1").set("color", "custom");
    model.component("comp1").geom("geom1").feature("difsel1")
         .set("customcolor", new double[]{0.5843137502670288, 0.3921568691730499, 0.501960813999176});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").feature().duplicate("adjsel2", "adjsel1");
    model.component("comp1").geom("geom1").feature("adjsel2").label("\u8f8a\u5b50\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"difsel1"});
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").feature().duplicate("difsel2", "difsel1");
    model.component("comp1").geom("geom1").feature("difsel2").label("\u56fa\u5b9a\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("difsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"imp1"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"adjsel1", "adjsel2"});
    model.component("comp1").geom("geom1").feature("difsel2").set("color", "none");
    model.component("comp1").geom("geom1").run("difsel2");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter("imp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("adjsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("difsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("adjsel2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("difsel2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u9009\u62e9");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

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

    model.component("comp1").physics("mbd").prop("AutoModeling").runCommand("createRigidDomains");
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u6750\u6599\uff1a\u5de6\u6846\u67b6");
    model.component("comp1").physics("mbd").feature("rd24")
         .label("\u521a\u6027\u6750\u6599\uff1a\u53f3\u6846\u67b6");
    model.component("comp1").physics("mbd").feature("rd2").label("\u521a\u6027\u6750\u6599\uff1a\u5bfc\u8f68 L1");
    model.component("comp1").physics("mbd").feature("rd4").label("\u521a\u6027\u6750\u6599\uff1a\u5bfc\u8f68 L2");
    model.component("comp1").physics("mbd").feature("rd18").label("\u521a\u6027\u6750\u6599\uff1a\u5bfc\u8f68 L3");
    model.component("comp1").physics("mbd").feature("rd26").label("\u521a\u6027\u6750\u6599\uff1a\u5bfc\u8f68 L4");
    model.component("comp1").physics("mbd").feature("rd35").label("\u521a\u6027\u6750\u6599\uff1a\u5bfc\u8f68 L5");
    model.component("comp1").physics("mbd").feature("rd3").label("\u521a\u6027\u6750\u6599\uff1a\u5bfc\u8f68 R1");
    model.component("comp1").physics("mbd").feature("rd23").label("\u521a\u6027\u6750\u6599\uff1a\u5bfc\u8f68 R2");
    model.component("comp1").physics("mbd").feature("rd27").label("\u521a\u6027\u6750\u6599\uff1a\u5bfc\u8f68 R3");
    model.component("comp1").physics("mbd").feature("rd30").label("\u521a\u6027\u6750\u6599\uff1a\u5bfc\u8f68 R4");
    model.component("comp1").physics("mbd").feature("rd34").label("\u521a\u6027\u6750\u6599\uff1a\u5bfc\u8f68 R5");
    model.component("comp1").physics("mbd").feature("rd15").label("\u521a\u6027\u6750\u6599\uff1a\u7403");
    model.component("comp1").physics("mbd").feature("rd44").label("\u521a\u6027\u6750\u6599\uff1a\u6258\u76d8");
    model.component("comp1").physics("mbd").feature().move("rd24", 4);
    model.component("comp1").physics("mbd").feature().move("rd4", 6);
    model.component("comp1").physics("mbd").feature().move("rd18", 7);
    model.component("comp1").physics("mbd").feature().move("rd26", 8);
    model.component("comp1").physics("mbd").feature().move("rd35", 9);
    model.component("comp1").physics("mbd").feature().move("rd23", 11);
    model.component("comp1").physics("mbd").feature().move("rd27", 12);
    model.component("comp1").physics("mbd").feature().move("rd30", 13);
    model.component("comp1").physics("mbd").feature().move("rd34", 14);
    model.component("comp1").physics("mbd").feature().move("rd44", 15);
    model.component("comp1").physics("mbd").feature().move("rd15", 16);
    model.component("comp1").physics("mbd").feature("rd5").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 1");
    model.component("comp1").physics("mbd").feature("rd6").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 2");
    model.component("comp1").physics("mbd").feature("rd7").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 3");
    model.component("comp1").physics("mbd").feature("rd8").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 4");
    model.component("comp1").physics("mbd").feature("rd9").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 5");
    model.component("comp1").physics("mbd").feature("rd10").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 6");
    model.component("comp1").physics("mbd").feature("rd11").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 7");
    model.component("comp1").physics("mbd").feature("rd12").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 8");
    model.component("comp1").physics("mbd").feature("rd13").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 9");
    model.component("comp1").physics("mbd").feature("rd14").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 10");
    model.component("comp1").physics("mbd").feature("rd16").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 11");
    model.component("comp1").physics("mbd").feature("rd17").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 12");
    model.component("comp1").physics("mbd").feature("rd19").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 13");
    model.component("comp1").physics("mbd").feature("rd20").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 14");
    model.component("comp1").physics("mbd").feature("rd21").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 15");
    model.component("comp1").physics("mbd").feature("rd22").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 16");
    model.component("comp1").physics("mbd").feature("rd25").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 17");
    model.component("comp1").physics("mbd").feature("rd28").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 18");
    model.component("comp1").physics("mbd").feature("rd29").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 19");
    model.component("comp1").physics("mbd").feature("rd31").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 20");
    model.component("comp1").physics("mbd").feature("rd32").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 21");
    model.component("comp1").physics("mbd").feature("rd33").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 22");
    model.component("comp1").physics("mbd").feature("rd36").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 23");
    model.component("comp1").physics("mbd").feature("rd37").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 24");
    model.component("comp1").physics("mbd").feature("rd38").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 25");
    model.component("comp1").physics("mbd").feature("rd39").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 26");
    model.component("comp1").physics("mbd").feature("rd40").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 27");
    model.component("comp1").physics("mbd").feature("rd41").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 28");
    model.component("comp1").physics("mbd").feature("rd42").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 29");
    model.component("comp1").physics("mbd").feature("rd43").label("\u521a\u6027\u6750\u6599\uff1a\u8f8a\u5b50 30");
    model.component("comp1").physics("mbd").feature("rd1").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd24").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd2").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd4").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd18").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd26").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd35").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd3").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd23").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd27").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd30").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd34").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd44").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "rd5");
    model.component("comp1").physics("mbd").feature("hgj1").feature("cjb1").selection().named("geom1_imp1_4_bnd");
    model.component("comp1").physics("mbd").feature("hgj1").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("hgj1").feature("ja1").selection().set(74);
    model.component("comp1").physics("mbd").feature("hgj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("omegap", "-omega");
    model.component("comp1").physics("mbd").feature().duplicate("hgj2", "hgj1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("hgj2").feature("cjb1").selection().named("geom1_imp1_5_bnd");
    model.component("comp1").physics("mbd").feature("hgj2").feature("ja1").selection().set(98);
    model.component("comp1").physics("mbd").feature().duplicate("hgj3", "hgj2");
    model.component("comp1").physics("mbd").feature("hgj3").set("Destination", "rd7");
    model.component("comp1").physics("mbd").feature("hgj3").feature("cjb1").selection().named("geom1_imp1_6_bnd");
    model.component("comp1").physics("mbd").feature("hgj3").feature("ja1").selection().set(122);
    model.component("comp1").physics("mbd").feature().duplicate("hgj4", "hgj3");
    model.component("comp1").physics("mbd").feature("hgj4").set("Destination", "rd8");
    model.component("comp1").physics("mbd").feature("hgj4").feature("cjb1").selection().named("geom1_imp1_7_bnd");
    model.component("comp1").physics("mbd").feature("hgj4").feature("ja1").selection().set(146);
    model.component("comp1").physics("mbd").feature().duplicate("hgj5", "hgj4");
    model.component("comp1").physics("mbd").feature("hgj5").set("Destination", "rd9");
    model.component("comp1").physics("mbd").feature("hgj5").feature("cjb1").selection().named("geom1_imp1_8_bnd");
    model.component("comp1").physics("mbd").feature("hgj5").feature("ja1").selection().set(170);
    model.component("comp1").physics("mbd").feature().duplicate("hgj6", "hgj5");
    model.component("comp1").physics("mbd").feature("hgj6").set("Destination", "rd10");
    model.component("comp1").physics("mbd").feature("hgj6").feature("cjb1").selection().named("geom1_imp1_9_bnd");
    model.component("comp1").physics("mbd").feature("hgj6").feature("ja1").selection().set(194);
    model.component("comp1").physics("mbd").feature().duplicate("hgj7", "hgj6");
    model.component("comp1").physics("mbd").feature("hgj7").set("Destination", "rd11");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("mbd").feature("hgj7").feature("cjb1").selection().named("geom1_imp1_10_bnd");
    model.component("comp1").physics("mbd").feature("hgj7").feature("ja1").selection().set(218);
    model.component("comp1").physics("mbd").feature().duplicate("hgj8", "hgj7");
    model.component("comp1").physics("mbd").feature("hgj8").set("Destination", "rd12");
    model.component("comp1").physics("mbd").feature("hgj8").feature("cjb1").selection().named("geom1_imp1_11_bnd");
    model.component("comp1").physics("mbd").feature("hgj8").feature("ja1").selection().set(242);
    model.component("comp1").physics("mbd").feature().duplicate("hgj9", "hgj8");
    model.component("comp1").physics("mbd").feature("hgj9").set("Destination", "rd13");
    model.component("comp1").physics("mbd").feature("hgj9").feature("cjb1").selection().named("geom1_imp1_12_bnd");
    model.component("comp1").physics("mbd").feature("hgj9").feature("ja1").selection().set(266);
    model.component("comp1").physics("mbd").feature().duplicate("hgj10", "hgj9");
    model.component("comp1").physics("mbd").feature("hgj10").set("Destination", "rd14");
    model.component("comp1").physics("mbd").feature("hgj10").feature("cjb1").selection().named("geom1_imp1_13_bnd");
    model.component("comp1").physics("mbd").feature("hgj10").feature("ja1").selection().set(290);
    model.component("comp1").physics("mbd").feature().duplicate("hgj11", "hgj10");
    model.component("comp1").physics("mbd").feature("hgj11").set("Destination", "rd16");
    model.component("comp1").physics("mbd").feature("hgj11").feature("cjb1").selection().named("geom1_imp1_15_bnd");
    model.component("comp1").physics("mbd").feature("hgj11").feature("ja1").selection().set(326);
    model.component("comp1").physics("mbd").feature().duplicate("hgj12", "hgj11");
    model.component("comp1").physics("mbd").feature("hgj12").set("Destination", "rd17");
    model.component("comp1").physics("mbd").feature("hgj12").feature("cjb1").selection().named("geom1_imp1_16_bnd");
    model.component("comp1").physics("mbd").feature("hgj12").feature("ja1").selection().set(350);
    model.component("comp1").physics("mbd").feature().duplicate("hgj13", "hgj12");
    model.component("comp1").physics("mbd").feature("hgj13").set("Destination", "rd19");
    model.component("comp1").physics("mbd").feature("hgj13").feature("cjb1").selection().named("geom1_imp1_18_bnd");
    model.component("comp1").physics("mbd").feature("hgj13").feature("ja1").selection().set(386);
    model.component("comp1").physics("mbd").feature().duplicate("hgj14", "hgj13");
    model.component("comp1").physics("mbd").feature("hgj14").set("Destination", "rd20");
    model.component("comp1").physics("mbd").feature("hgj14").feature("cjb1").selection().named("geom1_imp1_19_bnd");
    model.component("comp1").physics("mbd").feature("hgj14").feature("ja1").selection().set(410);
    model.component("comp1").physics("mbd").feature().duplicate("hgj15", "hgj14");
    model.component("comp1").physics("mbd").feature("hgj15").set("Destination", "rd21");
    model.component("comp1").physics("mbd").feature("hgj15").feature("cjb1").selection().named("geom1_imp1_20_bnd");
    model.component("comp1").physics("mbd").feature("hgj15").feature("ja1").selection().set(434);
    model.component("comp1").physics("mbd").feature().duplicate("hgj16", "hgj15");
    model.component("comp1").physics("mbd").feature("hgj16").set("Destination", "rd22");
    model.component("comp1").physics("mbd").feature("hgj16").feature("cjb1").selection().named("geom1_imp1_21_bnd");
    model.component("comp1").physics("mbd").feature("hgj16").feature("ja1").selection().set(458);
    model.component("comp1").physics("mbd").feature().duplicate("hgj17", "hgj16");
    model.component("comp1").physics("mbd").feature("hgj17").set("Destination", "rd25");
    model.component("comp1").physics("mbd").feature("hgj17").feature("cjb1").selection().named("geom1_imp1_24_bnd");
    model.component("comp1").physics("mbd").feature("hgj17").feature("ja1").selection().set(518);
    model.component("comp1").physics("mbd").feature().duplicate("hgj18", "hgj17");
    model.component("comp1").physics("mbd").feature("hgj18").set("Destination", "rd28");
    model.component("comp1").physics("mbd").feature("hgj18").feature("cjb1").selection().named("geom1_imp1_27_bnd");
    model.component("comp1").physics("mbd").feature("hgj18").feature("ja1").selection().set(566);
    model.component("comp1").physics("mbd").feature().duplicate("hgj19", "hgj18");
    model.component("comp1").physics("mbd").feature("hgj19").set("Destination", "rd29");
    model.component("comp1").physics("mbd").feature("hgj19").feature("cjb1").selection().named("geom1_imp1_28_bnd");
    model.component("comp1").physics("mbd").feature("hgj19").feature("ja1").selection().set(590);
    model.component("comp1").physics("mbd").feature().duplicate("hgj20", "hgj19");
    model.component("comp1").physics("mbd").feature("hgj20").set("Destination", "rd31");
    model.component("comp1").physics("mbd").feature("hgj20").feature("cjb1").selection().named("geom1_imp1_30_bnd");
    model.component("comp1").physics("mbd").feature("hgj20").feature("ja1").selection().set(626);
    model.component("comp1").physics("mbd").feature().duplicate("hgj21", "hgj20");
    model.component("comp1").physics("mbd").feature("hgj21").set("Destination", "rd32");
    model.component("comp1").physics("mbd").feature("hgj21").feature("cjb1").selection().named("geom1_imp1_31_bnd");
    model.component("comp1").physics("mbd").feature("hgj21").feature("ja1").selection().set(650);
    model.component("comp1").physics("mbd").feature().duplicate("hgj22", "hgj21");
    model.component("comp1").physics("mbd").feature("hgj22").set("Destination", "rd33");
    model.component("comp1").physics("mbd").feature("hgj22").set("ReverseDirection", true);
    model.component("comp1").physics("mbd").feature("hgj22").feature("cjb1").selection().named("geom1_imp1_32_bnd");
    model.component("comp1").physics("mbd").feature("hgj22").feature("ja1").selection().set(677);
    model.component("comp1").physics("mbd").feature().duplicate("hgj23", "hgj22");
    model.component("comp1").physics("mbd").feature("hgj23").set("Destination", "rd36");
    model.component("comp1").physics("mbd").feature("hgj23").feature("cjb1").selection().named("geom1_imp1_34_bnd");
    model.component("comp1").physics("mbd").feature("hgj23").feature("ja1").selection().set(725);
    model.component("comp1").physics("mbd").feature().duplicate("hgj24", "hgj23");
    model.component("comp1").physics("mbd").feature("hgj24").set("Destination", "rd37");
    model.component("comp1").physics("mbd").feature("hgj24").feature("cjb1").selection().named("geom1_imp1_35_bnd");
    model.component("comp1").physics("mbd").feature("hgj24").feature("ja1").selection().set(749);
    model.component("comp1").physics("mbd").feature().duplicate("hgj25", "hgj24");
    model.component("comp1").physics("mbd").feature("hgj25").set("Destination", "rd38");
    model.component("comp1").physics("mbd").feature("hgj25").feature("cjb1").selection().named("geom1_imp1_36_bnd");
    model.component("comp1").physics("mbd").feature("hgj25").feature("ja1").selection().set(773);
    model.component("comp1").physics("mbd").feature().duplicate("hgj26", "hgj25");
    model.component("comp1").physics("mbd").feature("hgj26").set("Destination", "rd39");
    model.component("comp1").physics("mbd").feature("hgj26").feature("cjb1").selection().named("geom1_imp1_37_bnd");
    model.component("comp1").physics("mbd").feature("hgj26").feature("ja1").selection().set(797);
    model.component("comp1").physics("mbd").feature().duplicate("hgj27", "hgj26");
    model.component("comp1").physics("mbd").feature("hgj27").set("Destination", "rd40");
    model.component("comp1").physics("mbd").feature("hgj27").feature("cjb1").selection().named("geom1_imp1_38_bnd");
    model.component("comp1").physics("mbd").feature("hgj27").feature("ja1").selection().set(821);
    model.component("comp1").physics("mbd").feature().duplicate("hgj28", "hgj27");
    model.component("comp1").physics("mbd").feature("hgj28").set("Destination", "rd41");
    model.component("comp1").physics("mbd").feature("hgj28").feature("cjb1").selection().named("geom1_imp1_39_bnd");
    model.component("comp1").physics("mbd").feature("hgj28").feature("ja1").selection().set(845);
    model.component("comp1").physics("mbd").feature().duplicate("hgj29", "hgj28");
    model.component("comp1").physics("mbd").feature("hgj29").set("Destination", "rd42");
    model.component("comp1").physics("mbd").feature("hgj29").feature("cjb1").selection().named("geom1_imp1_40_bnd");
    model.component("comp1").physics("mbd").feature("hgj29").feature("ja1").selection().set(869);
    model.component("comp1").physics("mbd").feature().duplicate("hgj30", "hgj29");
    model.component("comp1").physics("mbd").feature("hgj30").set("Destination", "rd43");
    model.component("comp1").physics("mbd").feature("hgj30").feature("cjb1").selection().named("geom1_imp1_41_bnd");
    model.component("comp1").physics("mbd").feature("hgj30").feature("ja1").selection().set(893);

    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("init1");
    model.nodeGroup("grp2").add("hgj1");
    model.nodeGroup("grp2").add("hgj2");
    model.nodeGroup("grp2").add("hgj3");
    model.nodeGroup("grp2").add("hgj4");
    model.nodeGroup("grp2").add("hgj5");
    model.nodeGroup("grp2").add("hgj6");
    model.nodeGroup("grp2").add("hgj7");
    model.nodeGroup("grp2").add("hgj8");
    model.nodeGroup("grp2").add("hgj9");
    model.nodeGroup("grp2").add("hgj10");
    model.nodeGroup("grp2").add("hgj11");
    model.nodeGroup("grp2").add("hgj12");
    model.nodeGroup("grp2").add("hgj13");
    model.nodeGroup("grp2").add("hgj14");
    model.nodeGroup("grp2").add("hgj15");
    model.nodeGroup("grp2").add("hgj16");
    model.nodeGroup("grp2").add("hgj17");
    model.nodeGroup("grp2").add("hgj18");
    model.nodeGroup("grp2").add("hgj19");
    model.nodeGroup("grp2").add("hgj20");
    model.nodeGroup("grp2").add("hgj21");
    model.nodeGroup("grp2").add("hgj22");
    model.nodeGroup("grp2").add("hgj23");
    model.nodeGroup("grp2").add("hgj24");
    model.nodeGroup("grp2").add("hgj25");
    model.nodeGroup("grp2").add("hgj26");
    model.nodeGroup("grp2").add("hgj27");
    model.nodeGroup("grp2").add("hgj28");
    model.nodeGroup("grp2").add("hgj29");
    model.nodeGroup("grp2").add("hgj30");
    model.nodeGroup("grp2").label("\u94f0\u94fe\u5173\u8282");

    model.component("comp1").physics("mbd").create("rbc1", "RigidBodyContact", -1);
    model.component("comp1").physics("mbd").feature("rbc1").set("Source", "rd15");
    model.component("comp1").physics("mbd").feature("rbc1").set("DestinationShape", "Cylindrical");
    model.component("comp1").physics("mbd").feature("rbc1").set("Destination", "rd5");
    model.component("comp1").physics("mbd").feature("rbc1").set("FiniteDestination", true);
    model.component("comp1").physics("mbd").feature("rbc1").set("fp", "fp");
    model.component("comp1").physics("mbd").feature("rbc1").create("fric1", "Friction", -1);
    model.component("comp1").physics("mbd").feature("rbc1").feature("fric1").set("mu", "mu");
    model.component("comp1").physics("mbd").feature("rbc1").feature("fric1").set("v0", "mbd.diag*1e-3[1/s]*10");
    model.component("comp1").physics("mbd").feature().duplicate("rbc2", "rbc1");
    model.component("comp1").physics("mbd").feature("rbc2").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature().duplicate("rbc3", "rbc2");
    model.component("comp1").physics("mbd").feature("rbc3").set("Destination", "rd7");
    model.component("comp1").physics("mbd").feature().duplicate("rbc4", "rbc3");
    model.component("comp1").physics("mbd").feature("rbc4").set("Destination", "rd8");
    model.component("comp1").physics("mbd").feature().duplicate("rbc5", "rbc4");
    model.component("comp1").physics("mbd").feature("rbc5").set("Destination", "rd9");
    model.component("comp1").physics("mbd").feature().duplicate("rbc6", "rbc5");
    model.component("comp1").physics("mbd").feature("rbc6").set("Destination", "rd10");
    model.component("comp1").physics("mbd").feature().duplicate("rbc7", "rbc6");
    model.component("comp1").physics("mbd").feature("rbc7").set("Destination", "rd11");
    model.component("comp1").physics("mbd").feature().duplicate("rbc8", "rbc7");
    model.component("comp1").physics("mbd").feature("rbc8").set("Destination", "rd12");
    model.component("comp1").physics("mbd").feature().duplicate("rbc9", "rbc8");
    model.component("comp1").physics("mbd").feature("rbc9").set("Destination", "rd13");
    model.component("comp1").physics("mbd").feature().duplicate("rbc10", "rbc9");
    model.component("comp1").physics("mbd").feature("rbc10").set("Destination", "rd14");
    model.component("comp1").physics("mbd").feature().duplicate("rbc11", "rbc10");
    model.component("comp1").physics("mbd").feature("rbc11").set("Destination", "rd16");
    model.component("comp1").physics("mbd").feature().duplicate("rbc12", "rbc11");
    model.component("comp1").physics("mbd").feature("rbc12").set("Destination", "rd17");
    model.component("comp1").physics("mbd").feature().duplicate("rbc13", "rbc12");
    model.component("comp1").physics("mbd").feature("rbc13").set("Destination", "rd19");
    model.component("comp1").physics("mbd").feature().duplicate("rbc14", "rbc13");
    model.component("comp1").physics("mbd").feature("rbc14").set("Destination", "rd20");
    model.component("comp1").physics("mbd").feature().duplicate("rbc15", "rbc14");
    model.component("comp1").physics("mbd").feature("rbc15").set("Destination", "rd21");
    model.component("comp1").physics("mbd").feature().duplicate("rbc16", "rbc15");
    model.component("comp1").physics("mbd").feature("rbc16").set("Destination", "rd22");
    model.component("comp1").physics("mbd").feature().duplicate("rbc17", "rbc16");
    model.component("comp1").physics("mbd").feature("rbc17").set("Destination", "rd25");
    model.component("comp1").physics("mbd").feature().duplicate("rbc18", "rbc17");
    model.component("comp1").physics("mbd").feature("rbc18").set("Destination", "rd28");
    model.component("comp1").physics("mbd").feature().duplicate("rbc19", "rbc18");
    model.component("comp1").physics("mbd").feature("rbc19").set("Destination", "rd29");
    model.component("comp1").physics("mbd").feature().duplicate("rbc20", "rbc19");
    model.component("comp1").physics("mbd").feature("rbc20").set("Destination", "rd31");
    model.component("comp1").physics("mbd").feature().duplicate("rbc21", "rbc20");
    model.component("comp1").physics("mbd").feature("rbc21").set("Destination", "rd32");
    model.component("comp1").physics("mbd").feature().duplicate("rbc22", "rbc21");
    model.component("comp1").physics("mbd").feature("rbc22").set("Destination", "rd33");
    model.component("comp1").physics("mbd").feature().duplicate("rbc23", "rbc22");
    model.component("comp1").physics("mbd").feature("rbc23").set("Destination", "rd36");
    model.component("comp1").physics("mbd").feature().duplicate("rbc24", "rbc23");
    model.component("comp1").physics("mbd").feature("rbc24").set("Destination", "rd37");
    model.component("comp1").physics("mbd").feature().duplicate("rbc25", "rbc24");
    model.component("comp1").physics("mbd").feature("rbc25").set("Destination", "rd38");
    model.component("comp1").physics("mbd").feature().duplicate("rbc26", "rbc25");
    model.component("comp1").physics("mbd").feature("rbc26").set("Destination", "rd39");
    model.component("comp1").physics("mbd").feature().duplicate("rbc27", "rbc26");
    model.component("comp1").physics("mbd").feature("rbc27").set("Destination", "rd40");
    model.component("comp1").physics("mbd").feature().duplicate("rbc28", "rbc27");
    model.component("comp1").physics("mbd").feature("rbc28").set("Destination", "rd41");
    model.component("comp1").physics("mbd").feature().duplicate("rbc29", "rbc28");
    model.component("comp1").physics("mbd").feature("rbc29").set("Destination", "rd42");
    model.component("comp1").physics("mbd").feature().duplicate("rbc30", "rbc29");
    model.component("comp1").physics("mbd").feature("rbc30").set("Destination", "rd43");
    model.component("comp1").physics("mbd").feature().duplicate("rbc31", "rbc30");
    model.component("comp1").physics("mbd").feature("rbc31").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature().duplicate("rbc32", "rbc31");
    model.component("comp1").physics("mbd").feature("rbc32").set("Destination", "rd4");
    model.component("comp1").physics("mbd").feature().duplicate("rbc33", "rbc32");
    model.component("comp1").physics("mbd").feature("rbc33").set("Destination", "rd18");
    model.component("comp1").physics("mbd").feature().duplicate("rbc34", "rbc33");
    model.component("comp1").physics("mbd").feature("rbc34").set("Destination", "rd26");
    model.component("comp1").physics("mbd").feature().duplicate("rbc35", "rbc34");
    model.component("comp1").physics("mbd").feature("rbc35").set("Destination", "rd35");
    model.component("comp1").physics("mbd").feature().duplicate("rbc36", "rbc35");
    model.component("comp1").physics("mbd").feature("rbc36").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature().duplicate("rbc37", "rbc36");
    model.component("comp1").physics("mbd").feature("rbc37").set("Destination", "rd23");
    model.component("comp1").physics("mbd").feature().duplicate("rbc38", "rbc37");
    model.component("comp1").physics("mbd").feature("rbc38").set("Destination", "rd27");
    model.component("comp1").physics("mbd").feature().duplicate("rbc39", "rbc38");
    model.component("comp1").physics("mbd").feature("rbc39").set("Destination", "rd30");
    model.component("comp1").physics("mbd").feature().duplicate("rbc40", "rbc39");
    model.component("comp1").physics("mbd").feature("rbc40").set("Destination", "rd34");
    model.component("comp1").physics("mbd").feature().duplicate("rbc41", "rbc40");
    model.component("comp1").physics("mbd").feature("rbc41").set("DestinationShape", "Planar");
    model.component("comp1").physics("mbd").feature("rbc41").selection("planarDestinationSelection").set(397);
    model.component("comp1").physics("mbd").feature().duplicate("rbc42", "rbc41");
    model.component("comp1").physics("mbd").feature("rbc42").selection("planarDestinationSelection").set(403);

    model.nodeGroup().create("grp3", "Physics", "mbd");
    model.nodeGroup("grp3").placeAfter("init1");
    model.nodeGroup("grp3").add("rbc1");
    model.nodeGroup("grp3").add("rbc2");
    model.nodeGroup("grp3").add("rbc3");
    model.nodeGroup("grp3").add("rbc4");
    model.nodeGroup("grp3").add("rbc5");
    model.nodeGroup("grp3").add("rbc6");
    model.nodeGroup("grp3").add("rbc7");
    model.nodeGroup("grp3").add("rbc8");
    model.nodeGroup("grp3").add("rbc9");
    model.nodeGroup("grp3").add("rbc10");
    model.nodeGroup("grp3").add("rbc11");
    model.nodeGroup("grp3").add("rbc12");
    model.nodeGroup("grp3").add("rbc13");
    model.nodeGroup("grp3").add("rbc14");
    model.nodeGroup("grp3").add("rbc15");
    model.nodeGroup("grp3").add("rbc16");
    model.nodeGroup("grp3").add("rbc17");
    model.nodeGroup("grp3").add("rbc18");
    model.nodeGroup("grp3").add("rbc19");
    model.nodeGroup("grp3").add("rbc20");
    model.nodeGroup("grp3").add("rbc21");
    model.nodeGroup("grp3").add("rbc22");
    model.nodeGroup("grp3").add("rbc23");
    model.nodeGroup("grp3").add("rbc24");
    model.nodeGroup("grp3").add("rbc25");
    model.nodeGroup("grp3").add("rbc26");
    model.nodeGroup("grp3").add("rbc27");
    model.nodeGroup("grp3").add("rbc28");
    model.nodeGroup("grp3").add("rbc29");
    model.nodeGroup("grp3").add("rbc30");
    model.nodeGroup("grp3").label("\u7403-\u8f8a\u5b50\u63a5\u89e6");
    model.nodeGroup().create("grp4", "Physics", "mbd");
    model.nodeGroup("grp4").placeAfter("init1");
    model.nodeGroup("grp4").add("rbc31");
    model.nodeGroup("grp4").add("rbc32");
    model.nodeGroup("grp4").add("rbc33");
    model.nodeGroup("grp4").add("rbc34");
    model.nodeGroup("grp4").add("rbc35");
    model.nodeGroup("grp4").add("rbc36");
    model.nodeGroup("grp4").add("rbc37");
    model.nodeGroup("grp4").add("rbc38");
    model.nodeGroup("grp4").add("rbc39");
    model.nodeGroup("grp4").add("rbc40");
    model.nodeGroup("grp4").label("\u7403-\u5bfc\u8f68\u63a5\u89e6");
    model.nodeGroup().create("grp5", "Physics", "mbd");
    model.nodeGroup("grp5").placeAfter("init1");
    model.nodeGroup("grp5").add("rbc41");
    model.nodeGroup("grp5").add("rbc42");
    model.nodeGroup("grp5").label("\u7403-\u6258\u76d8\u63a5\u89e6");

    model.component("comp1").physics("mbd").create("gacc1", "GravityAcceleration", -1);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection()
         .set(13, 19, 23, 29, 39, 49, 59, 69, 79, 89, 99, 109, 119, 137, 147, 157, 162, 163, 173, 183, 193, 203, 219, 229, 234, 235, 240, 241, 251, 261, 266, 267, 277, 290, 297, 302, 312, 322, 332, 342, 352, 362, 372, 382);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(397);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(917, 926);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 25);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().all();
    model.component("comp1").mesh("mesh1").feature("swe1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 45);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("geom1_sel2");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 50);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().named("geom1_sel3");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").selection().named("geom1_difsel1");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("numelem", 10);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(44);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(15);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", 0.03);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.01,8)");
    model.study("std1").feature("time").set("plot", true);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 15);
    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std1").createAutoSequences("all");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);

    model.sol("sol1").runAll();

    model.result().remove("pg1");

    model.study("std1").feature("time").set("plotgroup", "Default");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("descractive", true);
    model.result("pg2").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("rangecoloractive", "on");
    model.result("pg2").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg2").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg2").feature("vol1").set("colortable", "Cyclic");
    model.result("pg2").feature("vol1").set("colorlegend", false);
    model.result("pg2").feature("vol1").set("colortabletype", "discrete");
    model.result("pg2").feature("vol1").set("smooth", "none");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature("vol1").feature().create("def1", "Deform");
    model.result("pg2").feature("vol1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_adjsel1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("sel1").selection().named("geom1_adjsel2");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("colorlegend", false);
    model.result("pg1").feature("surf2").set("colortable", "WaveLight");
    model.result("pg1").feature().duplicate("surf3", "surf2");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("sel1").selection().named("geom1_difsel2");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("coloring", "uniform");
    model.result("pg1").feature("surf3").set("color", "gray");
    model.result("pg1").feature("surf3").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u901f\u5ea6 [\u7403]");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1")
         .setIndex("expr", "sqrt(mbd.rd15.u_tx^2+mbd.rd15.u_ty^2+mbd.rd15.u_tz^2)", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "m/s", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u521a\u4f53\u901f\u5ea6", 0);
    model.result("pg3").run();
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u89d2\u901f\u5ea6 [\u7403]");
    model.result("pg4").set("ylabel", "\u521a\u4f53\u89d2\u901f\u5ea6 (rad/s)");
    model.result("pg4").run();
    model.result("pg4").feature("glob1")
         .setIndex("expr", "sqrt(mbd.rd15.th_tx^2+mbd.rd15.th_ty^2+mbd.rd15.th_tz^2)", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "rad/s", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u521a\u4f53\u89d2\u901f\u5ea6", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u89d2\u901f\u5ea6\u5206\u91cf [\u7403]");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "mbd.rd15.th_tx", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "rad/s", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u521a\u4f53\u89d2\u901f\u5ea6\uff0cx \u5206\u91cf", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "mbd.rd15.th_ty", 1);
    model.result("pg5").feature("glob1").setIndex("unit", "rad/s", 1);

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg5").feature("glob1").setIndex("descr", "\u521a\u4f53\u89d2\u901f\u5ea6\uff0cy \u5206\u91cf", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "mbd.rd15.th_tz", 2);
    model.result("pg5").feature("glob1").setIndex("unit", "rad/s", 2);
    model.result("pg5").feature("glob1").setIndex("descr", "\u521a\u4f53\u89d2\u901f\u5ea6\uff0cz \u5206\u91cf", 2);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u63a5\u89e6\u529b\u548c\u6469\u64e6\u529b [\u8f8a\u5b50]");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("expr", new String[]{});
    model.result("pg6").feature("glob1").set("descr", new String[]{});
    model.result("pg6").feature("glob1").setIndex("expr", "mbd.rbc1.Fn", 0);
    model.result("pg6").feature("glob1").setIndex("unit", "N", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "mbd.rbc16.Fn", 1);
    model.result("pg6").feature("glob1").setIndex("unit", "N", 1);
    model.result("pg6").feature("glob1").setIndex("expr", "mbd.rbc30.Fn", 2);
    model.result("pg6").feature("glob1").setIndex("unit", "N", 2);
    model.result("pg6").feature("glob1").set("legendmethod", "manual");
    model.result("pg6").feature("glob1").setIndex("legends", "\u63a5\u89e6\u529b - \u8f8a\u5b50 1", 0);
    model.result("pg6").feature("glob1").setIndex("legends", "\u63a5\u89e6\u529b - \u8f8a\u5b50 16", 1);
    model.result("pg6").feature("glob1").setIndex("legends", "\u63a5\u89e6\u529b - \u8f8a\u5b50 30", 2);
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").setIndex("expr", "mbd.rbc1.Ff", 0);
    model.result("pg6").feature("glob2").setIndex("unit", "N", 0);
    model.result("pg6").feature("glob2").setIndex("descr", "\u6469\u64e6\u529b", 0);
    model.result("pg6").feature("glob2").setIndex("expr", "mbd.rbc16.Ff", 1);
    model.result("pg6").feature("glob2").setIndex("unit", "N", 1);
    model.result("pg6").feature("glob2").setIndex("descr", "\u6469\u64e6\u529b", 1);
    model.result("pg6").feature("glob2").setIndex("expr", "mbd.rbc30.Ff", 2);
    model.result("pg6").feature("glob2").setIndex("unit", "N", 2);
    model.result("pg6").feature("glob2").setIndex("descr", "\u6469\u64e6\u529b", 2);
    model.result("pg6").feature("glob2").set("linestyle", "dashed");
    model.result("pg6").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg6").feature("glob2").setIndex("legends", "\u6469\u64e6\u529b - \u8f8a\u5b50 1", 0);
    model.result("pg6").feature("glob2").setIndex("legends", "\u6469\u64e6\u529b - \u8f8a\u5b50 16", 1);
    model.result("pg6").feature("glob2").setIndex("legends", "\u6469\u64e6\u529b - \u8f8a\u5b50 30", 2);
    model.result("pg6").run();
    model.result("pg6").set("ylabel", "\u529b (N)");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u63a5\u89e6\u529b\u548c\u6469\u64e6\u529b [\u5bfc\u8f68]");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{});
    model.result("pg7").feature("glob1").set("descr", new String[]{});
    model.result("pg7").feature("glob1").setIndex("expr", "mbd.rbc33.Fn", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "N", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "mbd.rbc35.Fn", 1);
    model.result("pg7").feature("glob1").setIndex("unit", "N", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "mbd.rbc39.Fn", 2);
    model.result("pg7").feature("glob1").setIndex("unit", "N", 2);
    model.result("pg7").feature("glob1").setIndex("expr", "mbd.rbc40.Fn", 3);
    model.result("pg7").feature("glob1").setIndex("unit", "N", 3);
    model.result("pg7").feature("glob1").setIndex("legends", "\u63a5\u89e6\u529b - \u5bfc\u8f68 L3", 0);
    model.result("pg7").feature("glob1").setIndex("legends", "\u63a5\u89e6\u529b - \u5bfc\u8f68 L5", 1);
    model.result("pg7").feature("glob1").setIndex("legends", "\u63a5\u89e6\u529b - \u5bfc\u8f68 R4", 2);
    model.result("pg7").feature("glob1").setIndex("legends", "\u63a5\u89e6\u529b - \u5bfc\u8f68 R5", 3);
    model.result("pg7").run();
    model.result("pg7").feature("glob2").setIndex("expr", "mbd.rbc33.Ff", 0);
    model.result("pg7").feature("glob2").setIndex("unit", "N", 0);
    model.result("pg7").feature("glob2").setIndex("descr", "\u6469\u64e6\u529b", 0);
    model.result("pg7").feature("glob2").setIndex("expr", "mbd.rbc35.Ff", 1);
    model.result("pg7").feature("glob2").setIndex("unit", "N", 1);
    model.result("pg7").feature("glob2").setIndex("descr", "\u6469\u64e6\u529b", 1);
    model.result("pg7").feature("glob2").setIndex("expr", "mbd.rbc39.Ff", 2);
    model.result("pg7").feature("glob2").setIndex("unit", "N", 2);
    model.result("pg7").feature("glob2").setIndex("descr", "\u6469\u64e6\u529b", 2);
    model.result("pg7").feature("glob2").setIndex("expr", "mbd.rbc40.Ff", 3);
    model.result("pg7").feature("glob2").setIndex("unit", "N", 3);
    model.result("pg7").feature("glob2").setIndex("descr", "\u6469\u64e6\u529b", 3);
    model.result("pg7").feature("glob2").setIndex("legends", "\u6469\u64e6\u529b - \u5bfc\u8f68 L3", 0);
    model.result("pg7").feature("glob2").setIndex("legends", "\u6469\u64e6\u529b - \u5bfc\u8f68 L5", 1);
    model.result("pg7").feature("glob2").setIndex("legends", "\u6469\u64e6\u529b - \u5bfc\u8f68 R4", 2);
    model.result("pg7").feature("glob2").setIndex("legends", "\u6469\u64e6\u529b - \u5bfc\u8f68 R5", 3);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u63a5\u89e6\u529b\u548c\u6469\u64e6\u529b [\u6258\u76d8]");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("expr", new String[]{});
    model.result("pg8").feature("glob1").set("descr", new String[]{});
    model.result("pg8").feature("glob1").setIndex("expr", "mbd.rbc41.Fn", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "N", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "mbd.rbc42.Fn", 1);
    model.result("pg8").feature("glob1").setIndex("unit", "N", 1);
    model.result("pg8").feature("glob1").remove("legends", new int[]{0, 1, 2, 3});
    model.result("pg8").feature("glob1").setIndex("legends", "\u63a5\u89e6\u529b - \u6258\u76d8\u5e95\u90e8", 0);
    model.result("pg8").feature("glob1").setIndex("legends", "\u63a5\u89e6\u529b - \u6258\u76d8\u4fa7", 1);
    model.result("pg8").run();
    model.result("pg8").feature("glob2").set("expr", new String[]{});
    model.result("pg8").feature("glob2").set("descr", new String[]{});
    model.result("pg8").feature("glob2").setIndex("expr", "mbd.rbc41.Ff", 0);
    model.result("pg8").feature("glob2").setIndex("unit", "N", 0);
    model.result("pg8").feature("glob2").setIndex("descr", "\u6469\u64e6\u529b", 0);
    model.result("pg8").feature("glob2").setIndex("expr", "mbd.rbc42.Ff", 1);
    model.result("pg8").feature("glob2").setIndex("unit", "N", 1);
    model.result("pg8").feature("glob2").setIndex("descr", "\u6469\u64e6\u529b", 1);
    model.result("pg8").feature("glob2").remove("legends", new int[]{0, 1, 2, 3});
    model.result("pg8").feature("glob2").setIndex("legends", "\u6469\u64e6\u529b - \u6258\u76d8\u5e95\u90e8", 0);
    model.result("pg8").feature("glob2").setIndex("legends", "\u6469\u64e6\u529b - \u6258\u76d8\u4fa7", 1);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u6469\u64e6\u80fd\u8017\u7387 [\u8f8a\u5b50]");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").set("expr", new String[]{});
    model.result("pg9").feature("glob1").set("descr", new String[]{});
    model.result("pg9").feature("glob1").setIndex("expr", "mbd.rbc1.Qf", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "W", 0);
    model.result("pg9").feature("glob1").setIndex("expr", "mbd.rbc16.Qf", 1);
    model.result("pg9").feature("glob1").setIndex("unit", "W", 1);
    model.result("pg9").feature("glob1").setIndex("expr", "mbd.rbc30.Qf", 2);
    model.result("pg9").feature("glob1").setIndex("unit", "W", 2);
    model.result("pg9").feature("glob1").setIndex("legends", "\u8f8a\u5b50 1", 0);
    model.result("pg9").feature("glob1").setIndex("legends", "\u8f8a\u5b50 16", 1);
    model.result("pg9").feature("glob1").setIndex("legends", "\u8f8a\u5b50 30", 2);
    model.result("pg9").run();
    model.result("pg9").feature().remove("glob2");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").set("ylabelactive", false);
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u6469\u64e6\u80fd\u8017\u7387 [\u5bfc\u8f68]");
    model.result("pg10").run();
    model.result("pg10").feature("glob1").set("expr", new String[]{});
    model.result("pg10").feature("glob1").set("descr", new String[]{});
    model.result("pg10").feature("glob1").setIndex("expr", "mbd.rbc33.Qf", 0);
    model.result("pg10").feature("glob1").setIndex("unit", "W", 0);
    model.result("pg10").feature("glob1").setIndex("expr", "mbd.rbc35.Qf", 1);
    model.result("pg10").feature("glob1").setIndex("unit", "W", 1);
    model.result("pg10").feature("glob1").setIndex("expr", "mbd.rbc39.Qf", 2);
    model.result("pg10").feature("glob1").setIndex("unit", "W", 2);
    model.result("pg10").feature("glob1").setIndex("expr", "mbd.rbc40.Qf", 3);
    model.result("pg10").feature("glob1").setIndex("unit", "W", 3);
    model.result("pg10").feature("glob1").setIndex("legends", "\u5bfc\u8f68 L3", 0);
    model.result("pg10").feature("glob1").setIndex("legends", "\u5bfc\u8f68 L5", 1);
    model.result("pg10").feature("glob1").setIndex("legends", "\u5bfc\u8f68 R4", 2);
    model.result("pg10").feature("glob1").setIndex("legends", "\u5bfc\u8f68 R5", 3);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u6469\u64e6\u80fd\u8017\u7387 [\u6258\u76d8]");
    model.result("pg11").run();
    model.result("pg11").feature("glob1").set("expr", new String[]{});
    model.result("pg11").feature("glob1").set("descr", new String[]{});
    model.result("pg11").feature("glob1").setIndex("expr", "mbd.rbc41.Qf", 0);
    model.result("pg11").feature("glob1").setIndex("unit", "W", 0);
    model.result("pg11").feature("glob1").setIndex("expr", "mbd.rbc42.Qf", 1);
    model.result("pg11").feature("glob1").setIndex("unit", "W", 1);
    model.result("pg11").feature("glob1").setIndex("legends", "\u6258\u76d8\u5e95\u90e8", 0);
    model.result("pg11").feature("glob1").setIndex("legends", "\u6258\u76d8\u4fa7", 1);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg3").run();

    model.nodeGroup().create("grp6", "Results");
    model.nodeGroup("grp6").set("type", "plotgroup");
    model.nodeGroup("grp6").placeAfter("plotgroup", "pg2");
    model.nodeGroup("grp6").add("plotgroup", "pg3");
    model.nodeGroup("grp6").add("plotgroup", "pg4");
    model.nodeGroup("grp6").add("plotgroup", "pg5");
    model.nodeGroup("grp6").label("\u901f\u5ea6 [\u7403]");

    model.result("pg6").run();

    model.nodeGroup().create("grp7", "Results");
    model.nodeGroup("grp7").set("type", "plotgroup");
    model.nodeGroup("grp7").placeAfter("plotgroup", "pg2");
    model.nodeGroup().move("grp7", 6);
    model.nodeGroup("grp7").add("plotgroup", "pg6");
    model.nodeGroup("grp7").add("plotgroup", "pg7");
    model.nodeGroup("grp7").add("plotgroup", "pg8");
    model.nodeGroup("grp7").label("\u63a5\u89e6\u529b\u548c\u6469\u64e6\u529b");

    model.result("pg9").run();

    model.nodeGroup().create("grp8", "Results");
    model.nodeGroup("grp8").set("type", "plotgroup");
    model.nodeGroup("grp8").placeAfter("plotgroup", "pg2");
    model.nodeGroup().move("grp8", 7);
    model.nodeGroup("grp8").add("plotgroup", "pg9");
    model.nodeGroup("grp8").add("plotgroup", "pg10");
    model.nodeGroup("grp8").add("plotgroup", "pg11");
    model.nodeGroup("grp8").label("\u6469\u64e6\u80fd\u8017\u7387");

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
    model.result().export("anim1").label("\u4f4d\u79fb (mbd)");
    model.result().export("anim1").set("maxframes", 100);
    model.result("pg1").run();

    model.title("\u8f8a\u5b50\u8f93\u9001\u673a\u52a8\u529b\u5b66");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df\u8f93\u9001\u7403\u5f62\u7269\u4f53\u6216\u7403\u7684\u8f8a\u5b50\u8f93\u9001\u673a\u7684\u52a8\u529b\u5b66\u3002\u9996\u5148\u5c06\u7403\u653e\u7f6e\u5728\u8f8a\u5b50\u8f93\u9001\u673a\u7684\u4e00\u7aef\uff0c\u7136\u540e\u5c06\u5176\u8f93\u9001\u5230\u53e6\u4e00\u7aef\uff0c\u5e76\u5728\u6b64\u5904\u5c06\u5176\u6536\u96c6\u5728\u4e00\u4e2a\u77e9\u5f62\u6258\u76d8\u4e2d\u3002\u8f93\u9001\u7cfb\u7edf\u7531\u63d2\u5165\u4e24\u4e2a\u673a\u67b6\u4e4b\u95f4\u7684\u5706\u67f1\u6eda\u5b50\u7ec4\u6210\u3002\u8f93\u9001\u673a\u4e24\u4fa7\u5747\u8bbe\u6709\u5bfc\u8f68\uff0c\u4ee5\u9632\u6b62\u7403\u6389\u843d\u3002\u672c\u4f8b\u5047\u8bbe\u7cfb\u7edf\u7684\u6240\u6709\u90e8\u4ef6\u90fd\u662f\u521a\u6027\u7684\uff0c\u540c\u65f6\u5047\u8bbe\u6eda\u5b50\u4e0e\u7535\u6e90\u76f8\u8fde\uff0c\u5e76\u4ee5\u6307\u5b9a\u7684\u901f\u5ea6\u65cb\u8f6c\uff0c\u800c\u673a\u67b6\u3001\u5bfc\u8f68\u548c\u6258\u76d8\u5219\u662f\u56fa\u5b9a\u7684\u3002\n\n\u5176\u4e2d\u4f7f\u7528\u521a\u4f53\u6469\u64e6\u63a5\u89e6\u6765\u6a21\u62df\u7403\u4e0e\u6eda\u5b50\u4e4b\u95f4\u7684\u6469\u64e6\u63a5\u89e6\uff0c\u5728\u7403\u4e0e\u5176\u4ed6\u90e8\u4ef6\uff08\u5373\u5bfc\u8f68\u4e0e\u6258\u76d8\uff09\u4e4b\u95f4\u4e5f\u4f7f\u7528\u7c7b\u4f3c\u7684\u63a5\u89e6\uff0c\u5e76\u4f7f\u7528\u94f0\u94fe\u5173\u8282\u6765\u7b80\u5316\u6eda\u5b50\u4e0e\u673a\u67b6\u4e4b\u95f4\u7684\u8fde\u63a5\u3002\u901a\u8fc7\u6267\u884c\u77ac\u6001\u7814\u7a76\u6765\u5206\u6790\u7cfb\u7edf\u52a8\u529b\u5b66\uff0c\u5e76\u8ba1\u7b97\u51fa\u63a5\u89e6\u529b\u3001\u6469\u64e6\u529b\u4ee5\u53ca\u6469\u64e6\u4ea7\u751f\u7684\u80fd\u8017\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("roller_conveyor_dynamics.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
