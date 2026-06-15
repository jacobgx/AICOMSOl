/*
 * bracket_rom.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:20 by COMSOL 6.3.0.290. */
public class bracket_rom {

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

    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("InputParameters", "DampingRatios");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("f1", 200);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("zeta1", "1e-2");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("f2", 1500);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("zeta2", "2e-2");

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1")
         .setTableData(new double[][]{{34.33032811627975, 0.04393039272074171}, {40.50978717721011, 0.03737378713625216}, {46.68924623814046, 0.03257347791533529}, {52.86870529907082, 0.028913620878556527}, {59.048164360001174, 0.02603616708438414}, {65.22762342093154, 0.023718749295445794}, {71.40708248186189, 0.021815973548844188}, {77.58654154279225, 0.02022876608225108}, {83.7660006037226, 0.01888728801402523}, {89.94545966465296, 0.017740892826945923}, {96.12491872558331, 0.016751957393934886}, {102.30437778651367, 0.015891948739422703}, {108.48383684744402, 0.015138835072146913}, {114.66329590837438, 0.014475333969021394}, {120.84275496930474, 0.013887698048045816}, {127.02221403023509, 0.013364855093446946}, {133.20167309116545, 0.012897787527430093}, {139.3811321520958, 0.012479076948558232}, {145.56059121302616, 0.01210256468394026}, {151.7400502739565, 0.01176309528341846}, {157.91950933488687, 0.011456320236859805}, {164.09896839581722, 0.01117854603984168}, {170.27842745674758, 0.010926615341809266}, {176.45788651767793, 0.01069781306699364}, {182.6373455786083, 0.010489791593489338}, {188.81680463953865, 0.010300510624440335}, {194.996263700469, 0.010128188492169551}, {201.17572276139936, 0.009971262436973867}, {207.3551818223297, 0.009828355988386304}, {213.53464088326007, 0.009698252010141852}, {219.71409994419042, 0.009579870293805307}, {225.89355900512078, 0.009472248830040764}, {232.07301806605113, 0.009374528072045136}, {238.2524771269815, 0.0092859376478998}, {244.43193618791184, 0.009205785088464428}, {250.6113952488422, 0.009133446222924856}, {256.79085430977256, 0.00906835696108005}, {262.9703133707029, 0.009010006234262237}, {269.14977243163327, 0.00895792990868129}, {275.3292314925636, 0.008911705518418515}, {281.508690553494, 0.008870947692123692}, {287.68814961442433, 0.008835304169111538}, {293.8676086753547, 0.008804452318100201}, {300.04706773628504, 0.00877809608612853}, {306.2265267972154, 0.008755963316886953}, {312.40598585814575, 0.008737803387312362}, {318.5854449190761, 0.00872338511923448}, {324.76490398000647, 0.008712494929438907}, {330.9443630409368, 0.00870493518698452}, {337.1238221018672, 0.008700522751182495}, {343.30328116279753, 0.008699087667473605}, {405.0978717721011, 0.008818515820196547}, {466.89246238140464, 0.009113573609276757}, {528.6870529907083, 0.009522676616770781}, {590.4816436000118, 0.01001001994852544}, {652.2762342093154, 0.010553366880803505}, {714.0708248186189, 0.011138178017315242}, {775.8654154279225, 0.011754545981827829}, {837.660006037226, 0.012395486886177141}, {899.4545966465296, 0.01305593607864111}, {961.2491872558331, 0.013732131246511903}, {1023.0437778651367, 0.014421219092232583}, {1084.8383684744404, 0.015120996436676902}, {1146.632959083744, 0.015829735037536253}, {1208.4275496930477, 0.01654606015661059}, {1270.2221403023514, 0.017268864572322608}, {1332.016730911655, 0.01799724652689282}, {1393.8113215209587, 0.01873046418017753}, {1455.6059121302624, 0.019467901664887634}, {1517.400502739566, 0.020209043436007355}, {1579.1950933488697, 0.02095345464252339}, {1640.9896839581734, 0.021700765933993473}, {1702.784274567477, 0.02245066157536213}, {1764.5788651767807, 0.02320287005905247}, {1826.3734557860844, 0.023957156622873937}, {1888.168046395388, 0.02471331723714094}, {1949.9626370046917, 0.02547117373508576}, {2011.7572276139954, 0.026230569840738092}, {2073.551818223299, 0.026991367907051235}, {2135.3464088326027, 0.027753446220398686}, {2197.1409994419064, 0.02851669675993693}, {2258.93559005121, 0.02928102332473238}, {2320.7301806605137, 0.030046339960104716}, {2382.5247712698174, 0.03081256962886208}, {2444.319361879121, 0.03157964308409044}, {2506.1139524884247, 0.03234749790870838}, {2567.9085430977284, 0.0331160776936958}, {2629.703133707032, 0.03388533133218592}, {2691.4977243163357, 0.034655212410799725}, {2753.2923149256394, 0.03542567868294535}, {2815.086905534943, 0.036196691611487766}, {2876.8814961442467, 0.03696821597035845}, {2938.6760867535504, 0.037740219496429216}, {3000.470677362854, 0.038512672584403954}, {3062.2652679721577, 0.03928554801865169}, {3124.0598585814614, 0.04005882073686613}, {3185.854449190765, 0.040832467621230245}, {3247.6490398000687, 0.04160646731342258}, {3309.4436304093724, 0.04238080005034904}, {3371.238221018676, 0.04315544751794074}, {3433.0328116279798, 0.04393039272074176}});
    model.result().table("tbl1").label("\u963b\u5c3c\u6bd4\u56fe");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u963b\u5c3c\u6bd4\u56fe");
    model.result("pg1").create("tblp11", "Table");
    model.result("pg1").feature("tblp11").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp11").set("table", "tbl1");
    model.result("pg1").feature("tblp11").label("\u963b\u5c3c 1");
    model.result("pg1").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg1").set("ylabel", "\u963b\u5c3c\u6bd4");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.func().create("step1", "Step");
    model.func("step1").set("location", "1[ms]");
    model.func("step1").set("smooth", "2[ms]");
    model.func().create("rect1", "Rectangle");
    model.func("rect1").set("lower", "4[ms]");
    model.func("rect1").set("upper", "4.5[ms]");
    model.func("rect1").set("smoothactive", false);

    model.param().set("t", "0[s]");
    model.param().descr("t", "\u65f6\u95f4");
    model.param().set("F_x", "400[N]*rect1(t)");
    model.param().descr("F_x", "\u4f5c\u7528\u529b\uff0cx \u5206\u91cf");
    model.param().set("F_y", "500[N]*sin(2*pi*300[Hz]*t)");
    model.param().descr("F_y", "\u4f5c\u7528\u529b\uff0cy \u5206\u91cf");
    model.param().set("F_z", "-100[N]*step1(t)");

    return model;
  }

  public static Model run2(Model model) {
    model.param().descr("F_z", "\u4f5c\u7528\u529b\uff0cz \u5206\u91cf");

    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig1").selection().named("uni2");
    model.component("comp1").physics("solid").feature("rig1").create("rf1", "RigidBodyForce", -1);
    model.component("comp1").physics("solid").feature("rig1").feature("rf1")
         .set("Ft", new String[]{"F_x", "F_y", "F_z"});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").set("tunit", "ms");
    model.study("std1").feature("time").set("tlist", "range(0,0.2,10)");
    model.study("std1").feature("time").set("plot", true);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-4");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").label("\u9500\u4f4d\u79fb\uff0cx \u5206\u91cf");
    model.component("comp1").probe("var1").set("expr", "solid.rig1.u");
    model.component("comp1").probe("var1").set("descr", "\u521a\u4f53\u4f4d\u79fb\uff0cx \u5206\u91cf");
    model.component("comp1").probe("var1").set("unit", "mm");
    model.component("comp1").probe().duplicate("var2", "var1");
    model.component("comp1").probe("var2").label("\u9500\u4f4d\u79fb\uff0cy \u5206\u91cf");
    model.component("comp1").probe("var2").set("expr", "solid.rig1.v");
    model.component("comp1").probe().duplicate("var3", "var2");
    model.component("comp1").probe("var3").label("\u9500\u4f4d\u79fb\uff0cz \u5206\u91cf");
    model.component("comp1").probe("var3").set("expr", "solid.rig1.w");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 51, 0);
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg3").feature("vol1").set("rangecoloractive", true);
    model.result("pg3").feature("vol1").set("rangecolormax", 50);
    model.result("pg3").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("vol1").set("colorcalibration", -1);
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
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 51);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 36, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 41, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 46, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 51, 0);
    model.result("pg3").run();
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").label("\u9500\u65cb\u8f6c\u4f4d\u79fb\u7684\u4e2d\u5fc3");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u9500\u65cb\u8f6c\u4f4d\u79fb\u7684\u4e2d\u5fc3");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u4f4d\u79fb (mm)");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("linewidth", 2);
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "x \u5206\u91cf", 0);
    model.result("pg2").feature("tblp1").setIndex("legends", "y \u5206\u91cf", 1);
    model.result("pg2").feature("tblp1").setIndex("legends", "z \u5206\u91cf", 2);
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();

    model.title("\u652f\u67b6 - \u77ac\u6001\u5206\u6790");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u53d7\u65f6\u53d8\u8f7d\u8377\u4f5c\u7528\u7684\u652f\u67b6\u5185\u7684\u7ed3\u6784\u5e94\u529b\u3002");

    model.label("bracket_transient.mph");

    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result().table().duplicate("tbl3", "tbl2");
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("tblp2", "tblp1");
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").feature("tblp2").label("\u672a\u964d\u9636\u6a21\u578b");
    model.result("pg2").feature("tblp2").set("table", "tbl3");
    model.result("pg2").feature("tblp2").set("linestyle", "dotted");
    model.result("pg2").feature("tblp2").set("linecolor", "black");
    model.result("pg2").feature("tblp2").set("legend", false);
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();

    model.common().create("grmi1", "GlobalReducedModelInputs", "");
    model.common("grmi1").setIndex("name", "F_x", 0);
    model.common("grmi1").setIndex("name", "F_y", 1);
    model.common("grmi1").setIndex("name", "F_z", 2);

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 8);
    model.study("std2").feature("eig").set("useadvanceddisable", true);
    model.study("std2").feature("eig").set("disabledphysics", new String[]{"solid/lemm1/dmp1"});
    model.study("std2").label("\u6a21\u578b\u964d\u9636");
    model.study("std2").create("mr", "ModelReduction");
    model.study("std2").feature("mr").set("trainingStudy", "std2");
    model.study("std2").feature("mr").set("trainingStep", "eig");
    model.study("std2").feature("mr").set("unreducedModelStudy", "std1");
    model.study("std2").feature("mr").set("unreducedModelStep", "time");
    model.study("std2").feature("mr").set("qoiexpr", new String[]{"comp1.var1"});
    model.study("std2").feature("mr").set("qoidescr", new String[]{"\u9500\u4f4d\u79fb\uff0cx \u5206\u91cf"});
    model.study("std2").feature("mr").set("qoiexpr", new String[]{"comp1.var1", "comp1.var2"});
    model.study("std2").feature("mr")
         .set("qoidescr", new String[]{"\u9500\u4f4d\u79fb\uff0cx \u5206\u91cf", "\u9500\u4f4d\u79fb\uff0cy \u5206\u91cf"});
    model.study("std2").feature("mr").set("qoiexpr", new String[]{"comp1.var1", "comp1.var2", "comp1.var3"});
    model.study("std2").feature("mr")
         .set("qoidescr", new String[]{"\u9500\u4f4d\u79fb\uff0cx \u5206\u91cf", "\u9500\u4f4d\u79fb\uff0cy \u5206\u91cf", "\u9500\u4f4d\u79fb\uff0cz \u5206\u91cf"});
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset3");
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
    model.result("pg4").run();

    model.reduced("rom1").set("interf", "stateful");

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/solid", true);
    model.study("std3").feature("time").set("tunit", "ms");
    model.study("std3").feature("time").set("tlist", "range(0,0.2,10)");
    model.study("std3").feature("time").set("usertol", true);
    model.study("std3").feature("time").set("rtol", "1e-4");
    model.study("std3").feature("time").setSolveFor("/physics/solid", false);
    model.study("std3").feature("time").setSolveFor("/reduced/rom1", true);
    model.study("std3").feature("time").setEntry("reconstructors", "solid", "rom1");
    model.study("std3").label("\u964d\u9636\u6a21\u578b");
    model.study("std3").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");

    model.sol("sol4").runAll();

    model.result("pg4").run();
    model.result("pg4").set("data", "dset6");
    model.result("pg4").label("\u5e94\u529b\uff08\u964d\u9636\u6a21\u578b\uff09");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").set("rangecoloractive", true);
    model.result("pg4").feature("vol1").set("rangecolormax", 50);
    model.result("pg4").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("vol1").set("colorcalibration", -1);
    model.result("pg4").run();

    model.study("std1").label("\u672a\u964d\u9636\u6a21\u578b");

    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b\uff08\u672a\u964d\u9636\u6a21\u578b\uff09");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 36, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 41, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 46, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 51, 0);
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 36, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 41, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 46, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 51, 0);
    model.result("pg3").run();
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();

    model.func().create("an2", "Analytic");
    model.func("an2").set("expr", "rect1(x)");
    model.func("an2").set("periodic", true);
    model.func("an2").set("periodicupper", "10[ms]");
    model.func("an2").setIndex("argunit", "s", 0);

    model.param().set("F_x", "400[N]*an2(t)");

    model.study("std3").feature("time").set("tlist", "0 300");
    model.study("std3").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");

    model.sol("sol4").runAll();

    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 280);
    model.result("pg2").set("xmax", 300);
    model.result("pg2").set("ymin", -0.25);
    model.result("pg2").set("ymax", 0.21);
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();

    model.title("\u652f\u67b6 - \u964d\u9636\u5efa\u6a21");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u964d\u9636\u6a21\u578b\u6765\u8ba1\u7b97\u7ed3\u6784\u5728\u4e09\u4e2a\u5177\u6709\u72ec\u7acb\u65f6\u7a0b\u7684\u540c\u65f6\u8f7d\u8377\u4f5c\u7528\u4e0b\u7684\u54cd\u5e94\u3002\u964d\u9636\u6a21\u578b\u63d0\u4f9b\u4e86\u4e00\u79cd\u6709\u6548\u7684\u65b9\u6cd5\u6765\u5206\u6790\u7ebf\u6027\u7ed3\u6784\u52a8\u529b\u5b66\u95ee\u9898\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("romd1").clearSolutionData();

    model.label("bracket_rom.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
