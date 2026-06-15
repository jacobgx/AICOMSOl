/*
 * bracket_general_periodic.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:18 by COMSOL 6.3.0.290. */
public class bracket_general_periodic {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "bracket.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.param().set("Fh", "800[N]");
    model.param().set("dHole", "50[mm]");
    model.param().set("tArm", "8[mm]");
    model.param().set("p0", "Fh/(dHole*tArm*pi/4)");
    model.param().descr("p0", "\u5cf0\u503c\u8f7d\u8377\u5f3a\u5ea6");
    model.param().set("elSize", "6[mm]");
    model.param().descr("elSize", "\u5355\u5143\u5927\u5c0f");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5206\u5272\u5757");
    model.component("comp1").geom("geom1").feature("blk1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.025, 0.13, 0.04});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-0.11, -0.12, 0.025});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("mir2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").named("csel1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("cm1", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm1").selection("ent").set("par1", 2, 5);
    model.component("comp1").geom("geom1").run("cm1");
    model.component("comp1").geom("geom1").feature("cm1").setIndex("parname", "PinHoleX", 0);
    model.component("comp1").geom("geom1").feature("cm1").setIndex("parname", "PinHoleY", 1);
    model.component("comp1").geom("geom1").feature("cm1").setIndex("parname", "PinHoleZ", 2);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u87ba\u6813 1");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(41);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u87ba\u6813 2");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(43);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u87ba\u6813 3");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(55);
    model.component("comp1").selection("sel3").set("groupcontang", true);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u87ba\u6813 4");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(57);
    model.component("comp1").selection("sel4").set("groupcontang", true);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u87ba\u6813\u5b54");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2", "sel3", "sel4"});
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u5de6\u9500\u5b54");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(4);
    model.component("comp1").selection("sel5").set("groupcontang", true);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u53f3\u9500\u5b54");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(75);
    model.component("comp1").selection("sel6").set("groupcontang", true);
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u9500\u5b54");
    model.component("comp1").selection("uni2").set("entitydim", 2);
    model.component("comp1").selection("uni2").set("input", new String[]{"sel5", "sel6"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u87ba\u6813\u5b54\u7684\u8fb9");
    model.component("comp1").selection("adj1").set("entitydim", 2);
    model.component("comp1").selection("adj1").set("outputdim", 1);
    model.component("comp1").selection("adj1").set("input", new String[]{"uni1"});

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "load");
    model.component("comp1").func("an1").set("expr", "F*cos(atan2(py,abs(px)))");
    model.component("comp1").func("an1").set("args", "F, py, px");
    model.component("comp1").func("an1").setIndex("argunit", "Pa", 0);
    model.component("comp1").func("an1").setIndex("argunit", "m", 1);
    model.component("comp1").func("an1").setIndex("argunit", "m", 2);
    model.component("comp1").func("an1").set("fununit", "Pa");

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
         .set("funcs", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("filecolumns", 0);
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("columnKeys", new String[]{});
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
    model.component("comp1").material("mat1").propertyGroup("Swift").label("Swift");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").label("Voce");
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

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("uni1");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("adj1");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1, 4, 5, 6, 9);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(1, 33, 37, 50, 72);
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "elSize");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "elSize");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(24, 28, 63, 70);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").run();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();

    model.title("\u652f\u67b6\u51e0\u4f55");

    model
         .description("\u672c\u4f8b\u662f\u6240\u6709\u652f\u67b6\u6559\u7a0b\u7684\u57fa\u7840\uff0c\u5176\u4e2d\u5305\u542b\u652f\u67b6\u51e0\u4f55\u7ed3\u6784\u3002");

    model.label("bracket_basic.mph");

    model.param().set("A0", "750[N]");
    model.param().descr("A0", "\u5cf0\u503c\u8f7d\u8377\u5f3a\u5ea6");
    model.param().set("M0", "0[N]");
    model.param().descr("M0", "\u5e73\u5747\u8f7d\u8377\u5f3a\u5ea6");
    model.param().set("f0", "40[Hz]");
    model.param().descr("f0", "\u57fa\u9891");
    model.param().set("T0", "1/f0");
    model.param().descr("T0", "\u57fa\u672c\u5468\u671f");

    model.func().create("int1", "Interpolation");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", "M0", 0, 1);
    model.func("int1").setIndex("table", "T0/4", 1, 0);
    model.func("int1").setIndex("table", "M0+A0", 1, 1);
    model.func("int1").setIndex("table", "3/4*T0", 2, 0);
    model.func("int1").setIndex("table", "M0-A0", 2, 1);
    model.func("int1").setIndex("table", "T0", 3, 0);
    model.func("int1").setIndex("table", "M0", 3, 1);
    model.func("int1").setIndex("argunit", "s", 0);
    model.func("int1").setIndex("fununit", "N", 0);
    model.func().create("an2", "Analytic");
    model.func("an2").set("funcname", "loadHistory");
    model.func("an2").set("expr", "int1(x)");
    model.func("an2").set("periodic", true);
    model.func("an2").set("periodicupper", "T0");
    model.func("an2").setIndex("argunit", "s", 0);
    model.func("an2").set("fununit", "N");
    model.func("an2").setIndex("plotargs", "5*T0", 0, 2);
    model.func().duplicate("an3", "an2");
    model.func("an3").set("funcname", "Periodic");
    model.func("an3").set("expr", "int1(x)/A0");
    model.func("an3").set("fununit", "1");

    model.component().create("comp2", true);

    model.component("comp2").physics().create("ge", "GlobalEquations");
    model.component("comp2").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp2").physics("ge").feature("ge1").setIndex("name", "P", 0, 0);
    model.component("comp2").physics("ge").feature("ge1").setIndex("equation", "P-loadHistory(t)", 0, 0);
    model.component("comp2").physics("ge").feature("ge1").setIndex("initialValueU", "loadHistory(0)", 0, 0);
    model.component("comp2").physics("ge").feature("ge1").set("DependentVariableQuantity", "force");
    model.component("comp2").physics("ge").feature("ge1").set("SourceTermQuantity", "force");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/ge", true);
    model.study("std1").label("\u7814\u7a76 1\uff1a\u5085\u91cc\u53f6\u7cfb\u6570\u7684\u4ea7\u751f");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "range(0,T0/1000,T0)");
    model.study("std1").feature("time").setSolveFor("/physics/solid", false);
    model.study("std1").create("tffft", "TimeToFreqFFT");
    model.study("std1").feature("tffft").set("fftendtime", "T0");
    model.study("std1").feature("tffft").set("fftmaxfreq", 10000);
    model.study("std1").feature("tffft").setSolveFor("/physics/solid", false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("consistent", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u5085\u91cc\u53f6\u7cfb\u6570\uff0c\u8ba1\u7b97\u503c vs. \u89e3\u6790\u89e3");
    model.result("pg1").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg1").setIndex("looplevelindices", "range(1,1,100)", 0);
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "freq (Hz)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u5085\u91cc\u53f6\u7cfb\u6570 (N)");
    model.result("pg1").set("ylog", true);
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").label("\u5168\u5c40\uff1a\u8ba1\u7b97\u503c");
    model.result("pg1").feature("glob1").setIndex("expr", "abs(comp2.P)", 0);
    model.result("pg1").feature("glob1").set("linestyle", "none");
    model.result("pg1").feature("glob1").set("linemarker", "circle");
    model.result("pg1").feature("glob1").set("linewidth", 3);
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\u8ba1\u7b97\u503c", 0);
    model.result("pg1").feature().duplicate("glob2", "glob1");
    model.result("pg1").run();
    model.result("pg1").feature("glob2").label("\u5168\u5c40\uff1a\u89e3\u6790\u503c");
    model.result("pg1").feature("glob2").setIndex("expr", "8*A0/(pi^2*(freq/f0)^2)", 0);
    model.result("pg1").feature("glob2").set("data", "dset1");
    model.result("pg1").feature("glob2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg1").feature("glob2").setIndex("looplevelindices", "range(2,2,100)", 0);
    model.result("pg1").feature("glob2").set("linemarker", "plus");
    model.result("pg1").feature("glob2").setIndex("legends", "\u89e3\u6790\u503c", 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u5085\u91cc\u53f6\u7cfb\u6570\uff1b\u5b9e\u6570\u548c\u865a\u6570");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg2").setIndex("looplevelindices", "range(2,2,100)", 0);
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("ylog", true);
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "freq (Hz)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5085\u91cc\u53f6\u7cfb\u6570 (N)");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "abs(real(comp2.P))", 0);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("glob1").setIndex("expr", "abs(imag(comp2.P))", 1);
    model.result("pg2").feature("glob1").set("linestyle", "none");
    model.result("pg2").feature("glob1").set("linemarker", "circle");
    model.result("pg2").feature("glob1").set("linewidth", 3);
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "\u5b9e\u6570", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "\u865a\u6570", 1);
    model.result("pg2").run();

    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("InputParameters", "DampingRatios");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("f1", 100);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("zeta1", 0.03);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("f2", 300);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("zeta2", 0.03);

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1")
         .setTableData(new double[][]{{17.320508075688775, 0.13120284867334248}, {20.438199529312755, 0.11162084002204235}, {23.555890982936734, 0.09728419959406275}, {26.673582436560714, 0.08635364242797958}, {29.791273890184694, 0.07775981680203466}, {32.908965343808674, 0.07083859901534659}, {36.02665679743265, 0.065155754340416}, {39.14434825105663, 0.06041538831691147}, {42.2620397046806, 0.0564089196039436}, {45.37973115830458, 0.0529850869237682}, {48.497422611928556, 0.05003152475577529}, {51.61511406555253, 0.047463016295743905}, {54.73280551917651, 0.04521376122649072}, {57.850496972800485, 0.04323214373034502}, {60.96818842642446, 0.04147710577052327}, {64.08587988004844, 0.03991557898226468}, {67.20357133367241, 0.03852063139839292}, {70.32126278729639, 0.037270107164137346}, {73.43895424092037, 0.03614561273968834}, {76.55664569454434, 0.035131750817965456}, {79.67433714816832, 0.03421553410517042}, {82.7920286017923, 0.0333859315525959}, {85.90972005541627, 0.03263351339279854}, {89.02741150904025, 0.03195017075961107}, {92.14510296266423, 0.031328892227400165}, {95.2627944162882, 0.03076358422988814}, {98.38048586991218, 0.03024892562469163}, {101.49817732353615, 0.029780249061665535}, {104.61586877716013, 0.02935344356352971}, {107.73356023078411, 0.028964874021756702}, {110.85125168440808, 0.028611314277528402}, {113.96894313803206, 0.02828989118636546}, {117.08663459165604, 0.02799803761917685}, {120.20432604528001, 0.027733452777267166}, {123.32201749890399, 0.027494068526977618}, {126.43970895252797, 0.02727802071495547}, {129.55740040615194, 0.027083624625069136}, {132.67509185977593, 0.026909353895705895}, {135.79278331339992, 0.026753822341319518}, {138.9104747670239, 0.026615768221948968}, {142.0281662206479, 0.026494040584556622}, {145.1458576742719, 0.026387587364671325}, {148.26354912789589, 0.02629544498922605}, {151.38124058151988, 0.02621672926417072}, {154.49893203514387, 0.02615062736537859}, {157.61662348876786, 0.0260963907800824}, {160.73431494239185, 0.026053329069781306}, {163.85200639601584, 0.02602080434520497}, {166.96969784963983, 0.02599822636026508}, {170.08738930326382, 0.025985048145572256}, {173.2050807568878, 0.02598076211353316}, {204.3819952931276, 0.026337447152718955}, {235.5589098293674, 0.0272186690142368}, {266.7358243656072, 0.02844049920194429}, {297.912738901847, 0.029896002543665604}, {329.08965343808677, 0.0315187666693126}, {360.26656797432656, 0.03326536810613535}, {391.44348251056635, 0.0351062174081007}, {422.62039704680615, 0.03702045644111971}, {453.79731158304594, 0.03899295907741798}, {484.97422611928573, 0.04101248876493449}, {516.1511406555255, 0.04307052382324716}, {547.3280551917653, 0.04516048422063765}, {578.5049697280051, 0.04727720837533888}, {609.6818842642449, 0.049416590483672515}, {640.8587988004847, 0.05157532370916245}, {672.0357133367245, 0.05375071485509108}, {703.2126278729643, 0.05594054833598133}, {734.3895424092041, 0.05814298479785223}, {765.5664569454439, 0.060356484509995756}, {796.7433714816837, 0.06257974874303206}, {827.9202860179234, 0.0648116743920904}, {859.0972005541632, 0.06705131848042648}, {890.274115090403, 0.06929787012142354}, {921.4510296266428, 0.07155062817251824}, {952.6279441628826, 0.07380898327708284}, {983.8048586991224, 0.076072403320879}, {1014.9817732353622, 0.07834042156889219}, {1046.158687771602, 0.08061262692339442}, {1077.3356023078418, 0.08288865587353292}, {1108.5125168440816, 0.08516818580342589}, {1139.6894313803214, 0.08745092939862541}, {1170.8663459165612, 0.08973662994622235}, {1202.043260452801, 0.0920250573663472}, {1233.2201749890407, 0.09431600484563403}, {1264.3970895252805, 0.09660928596874761}, {1295.5740040615203, 0.0989047322640748}, {1326.7509185977601, 0.10120219109545428}, {1357.927833134, 0.10350152384433145}, {1389.1047476702397, 0.1058026043367102}, {1420.2816622064795, 0.10810531747728677}, {1451.4585767427193, 0.11040955805961405}, {1482.635491278959, 0.1127152297263853}, {1513.8124058151989, 0.11502224405819558}, {1544.9893203514387, 0.11733051977263216}, {1576.1662348876785, 0.11963998201841836}, {1607.3431494239182, 0.12195056175170406}, {1638.520063960158, 0.12426219518356224}, {1669.6969784963978, 0.12657482328938405}, {1700.8738930326376, 0.12888839137223057}, {1732.0508075688774, 0.13120284867334248}});
    model.result().table("tbl1").label("\u963b\u5c3c\u6bd4\u56fe");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u963b\u5c3c\u6bd4\u56fe");
    model.result("pg3").create("tblp11", "Table");
    model.result("pg3").feature("tblp11").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp11").set("table", "tbl1");
    model.result("pg3").feature("tblp11").label("\u963b\u5c3c 1");
    model.result("pg3").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg3").set("ylabel", "\u963b\u5c3c\u6bd4");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").label("\u8fb9\u754c\u8f7d\u8377\uff0c\u8c10\u6ce2");
    model.component("comp1").physics("solid").feature("bndl1").selection().named("uni2");
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("force", new String[]{"withsol('sol1',comp2.P,setval(freq,freq))", "0", "0"});
    model.component("comp1").physics("solid").feature("bndl1").set("harmonicPerturbation", true);

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std2").feature("eig").setSolveFor("/physics/ge", true);
    model.study("std2").create("frmod", "Frequencymodal");
    model.study("std2").feature("frmod").set("outputmap", new String[]{});
    model.study("std2").feature("frmod").setSolveFor("/physics/solid", true);
    model.study("std2").feature("frmod").setSolveFor("/physics/ge", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u9891\u57df\u6a21\u6001 + FFT");
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 12);
    model.study("std2").feature("eig").set("useadvanceddisable", true);
    model.study("std2").feature("eig").set("disabledphysics", new String[]{"solid/lemm1/dmp1"});
    model.study("std2").feature("eig").setSolveFor("/physics/ge", false);
    model.study("std2").feature("frmod").set("plist", "range(f0,f0,40*f0)");
    model.study("std2").feature("frmod").set("useadvanceddisable", true);
    model.study("std2").feature("frmod").setSolveFor("/physics/ge", false);
    model.study("std2").create("ftfft", "FreqToTimeFFT");
    model.study("std2").feature("ftfft").set("fftouttrange", "range(0,T0/100,T0)");
    model.study("std2").feature("ftfft").set("fftscaling", "discrete");
    model.study("std2").feature("ftfft").set("useadvanceddisable", true);
    model.study("std2").feature("ftfft").setSolveFor("/physics/ge", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 101, 0);
    model.result("pg4").label("\u5e94\u529b (solid)");
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
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 89, 0);
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").label("\u5e94\u529b\uff1a\u9891\u57df\u6a21\u6001 + FFT");
    model.result("pg4").run();
    model.result("pg4").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb\u5927\u5c0f[\u9891\u7387\u54cd\u5e94]");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("data", "dset5");
    model.result("pg5").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg5").setIndex("looplevelindices", "range(1,1,10)", 0);
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(1);
    model.result("pg5").feature("ptgr1").set("expr", "solid.uAmpX");
    model.result("pg5").feature("ptgr1").set("descr", "\u4f4d\u79fb\u5927\u5c0f\uff0cX \u5206\u91cf");
    model.result("pg5").feature("ptgr1").set("linestyle", "none");
    model.result("pg5").feature("ptgr1").set("linemarker", "diamond");
    model.result("pg5").run();

    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2")
         .label("\u8fb9\u754c\u8f7d\u8377\uff0c\u65f6\u57df\u5927\u5c0f");
    model.component("comp1").physics("solid").feature("bndl2").selection().named("uni2");
    model.component("comp1").physics("solid").feature("bndl2").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl2").set("force", new String[]{"A0", "0", "0"});

    model.study("std2").feature("frmod").set("disabledphysics", new String[]{"solid/bndl2"});
    model.study("std2").feature("ftfft").set("disabledphysics", new String[]{"solid/bndl2"});
    model.study().create("std3");
    model.study("std3").create("eig", "Eigenfrequency");
    model.study("std3").feature("eig").set("plotgroup", "Default");
    model.study("std3").feature("eig").set("storefact", false);
    model.study("std3").feature("eig").set("outputmap", new String[]{});
    model.study("std3").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std3").feature("eig").setSolveFor("/physics/ge", true);
    model.study("std3").create("timod", "Transientmodal");
    model.study("std3").feature("timod").set("initialtime", "0");
    model.study("std3").feature("timod").set("linpsolnum", "auto");
    model.study("std3").feature("timod").set("outputmap", new String[]{});
    model.study("std3").feature("timod").setSolveFor("/physics/solid", true);
    model.study("std3").feature("timod").setSolveFor("/physics/ge", true);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u77ac\u6001\u6a21\u6001[\u9a8c\u8bc1]");
    model.study("std3").feature().remove("eig");
    model.study("std3").feature("timod").set("tlist", "-20*T0 range(0,T0/100,T0)");
    model.study("std3").feature("timod").set("loadfact", "Periodic(t)");
    model.study("std3").feature("timod").set("useadvanceddisable", true);
    model.study("std3").feature("timod").set("disabledphysics", new String[]{"solid/bndl1"});
    model.study("std3").feature("timod").setSolveFor("/physics/ge", false);
    model.study("std3").showAutoSequences("all");

    model.sol("sol6").feature("mo1").set("maxstepconstraintbdf", "const");
    model.sol("sol6").feature("mo1").set("maxstepbdf", "5e-5");
    model.sol("sol6").feature("mo1").set("eigsol", "sol3");
    model.sol("sol6").feature("mo1").set("eigsoluse", "sol4");

    model.study("std3").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset6");
    model.result("pg6").setIndex("looplevel", 102, 0);
    model.result("pg6").label("\u5e94\u529b (solid)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg6").feature("vol1").set("threshold", "manual");
    model.result("pg6").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("vol1").set("colortable", "Rainbow");
    model.result("pg6").feature("vol1").set("colortabletrans", "none");
    model.result("pg6").feature("vol1").set("colorscalemode", "linear");
    model.result("pg6").feature("vol1").set("resolution", "custom");
    model.result("pg6").feature("vol1").set("refine", 2);
    model.result("pg6").feature("vol1").set("colortable", "Prism");
    model.result("pg6").feature("vol1").create("def", "Deform");
    model.result("pg6").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 90, 0);
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").label("\u5e94\u529b\uff1a\u77ac\u6001\u6a21\u6001");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset6");
    model.result("pg7").setIndex("looplevel", 102, 0);
    model.result("pg7").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg7").set("showlegends", true);
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg7").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg7").feature("surf1").set("coloring", "uniform");
    model.result("pg7").feature("surf1").set("color", "gray");
    model.result("pg7").feature("surf1").set("inheritcolor", false);
    model.result("pg7").feature("surf1").set("inheritrange", false);
    model.result("pg7").feature("surf1").set("inherittransparency", false);
    model.result("pg7").feature("surf1").create("def", "Deform");
    model.result("pg7").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg7").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg7").feature("surf1").feature("def").set("scale", 0);
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg7").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88);
    model.result("pg7").feature("surf1").create("tran1", "Transparency");
    model.result("pg7").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1")
         .set("expr", new String[]{"solid.bndl2.fax", "solid.bndl2.fay", "solid.bndl2.faz"});
    model.result("pg7").feature("arws1").set("placement", "gausspoints");
    model.result("pg7").feature("arws1").set("arrowbase", "tail");
    model.result("pg7").feature("arws1").label("\u8fb9\u754c\u8f7d\u8377\uff0c\u65f6\u57df\u5927\u5c0f");
    model.result("pg7").feature("arws1").set("inheritplot", "none");
    model.result("pg7").feature("arws1").create("col", "Color");
    model.result("pg7").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg7").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg7").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg7").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg7").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg7").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg7").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg7").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg7").feature("arws1").set("color", "red");
    model.result("pg7").feature("arws1").create("def", "Deform");
    model.result("pg7").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg7").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg7").feature("arws1").feature("def").set("scale", 0);
    model.result("pg7").feature().move("surf1", 1);
    model.result("pg7").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").set("data", "dset3");
    model.result("pg8").label("\u5c16\u7aef\u4f4d\u79fb");
    model.result("pg8").set("titletype", "none");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").selection().set(1);
    model.result("pg8").feature("ptgr1").set("expr", "u");
    model.result("pg8").run();
    model.result("pg8").feature("ptgr1").set("legend", true);
    model.result("pg8").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg8").feature("ptgr1").setIndex("legends", "\u9891\u57df\u6a21\u6001 + FFT", 0);
    model.result("pg8").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg8").run();
    model.result("pg8").feature("ptgr2").set("data", "dset6");
    model.result("pg8").feature("ptgr2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg8").feature("ptgr2").setIndex("looplevelindices", "range(2,102)", 0);
    model.result("pg8").feature("ptgr2").setIndex("legends", "\u77ac\u6001\u6a21\u6001", 0);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u5706\u89d2\u5e94\u529b");
    model.result("pg9").run();
    model.result("pg9").feature("ptgr1").selection().set(34);
    model.result("pg9").feature("ptgr1").set("expr", "solid.sx");
    model.result("pg9").run();
    model.result("pg9").feature("ptgr2").selection().set(34);
    model.result("pg9").feature("ptgr2").set("expr", "solid.sx");
    model.result("pg9").run();
    model.result("pg4").run();

    model.title("\u652f\u67b6 - \u4e00\u822c\u5468\u671f\u6027\u52a8\u529b\u5b66\u5206\u6790");

    model
         .description("\u5728\u672c\u4f8b\u4e2d\uff0c\u652f\u67b6\u53d7\u5230\u975e\u8c10\u6ce2\u7684\u5468\u671f\u8f7d\u8377\u4f5c\u7528\u3002\u9996\u5148\u4f7f\u7528\u201c\u65f6\u9891 FFT\u201d\u7814\u7a76\u6b65\u9aa4\u5c06\u52a0\u8f7d\u51fd\u6570\u5206\u89e3\u4e3a\u5085\u91cc\u53f6\u7ea7\u6570\uff0c\u5e76\u91c7\u7528\u6807\u51c6\u9891\u7387\u626b\u63cf\u6765\u8ba1\u7b97\u5bf9\u4e00\u7cfb\u5217\u8c10\u6ce2\u7684\u54cd\u5e94\uff0c\u7136\u540e\u6267\u884c\u9006 FFT \u7814\u7a76\u5c06\u7ed3\u679c\u8f6c\u6362\u56de\u65f6\u57df\u3002\u6700\u540e\uff0c\u4f7f\u7528\u201c\u77ac\u6001\u6a21\u6001\u201d\u7814\u7a76\u5bf9\u7ed3\u679c\u8fdb\u884c\u9a8c\u8bc1\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("bracket_general_periodic.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
