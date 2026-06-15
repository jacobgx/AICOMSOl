/*
 * main_bearing_cap.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:03 by COMSOL 6.3.0.290. */
public class main_bearing_cap {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Contact_and_Friction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("bolt", "BoltPretension");
    model.study("std1").feature("bolt").set("solnum", "auto");
    model.study("std1").feature("bolt").set("notsolnum", "auto");
    model.study("std1").feature("bolt").set("outputmap", new String[]{});
    model.study("std1").feature("bolt").set("ngenAUX", "1");
    model.study("std1").feature("bolt").set("goalngenAUX", "1");
    model.study("std1").feature("bolt").set("ngenAUX", "1");
    model.study("std1").feature("bolt").set("goalngenAUX", "1");
    model.study("std1").feature("bolt").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "main_bearing_cap_geom.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("imp1(1)", 4);
    model.geom()
         .load(new String[]{"part1"}, "Structural_Mechanics_Module\\Bolts\\hex_bolt_drill.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").label("M8 1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hgrip", "13[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hthic", "5.5[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "ndia", "8[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "sdia", "8[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "blen", "55[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "tlen", "22[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dhrc", "0.2[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dtc", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dhtc", "5[mm]");
    model.component("comp1").geom("geom1").feature("pi1").set("workplanepart", "wp1");
    model.component("comp1").geom("geom1").feature("pi1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u87ba\u6813");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_sel3.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_sel3.dom", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel5", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel8", true);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").feature().duplicate("pi2", "pi1");
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none", "none", "none"});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selkeepbnd", new String[]{"off", "off", "on", "off", "off", "on"});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetodom", new String[]{"none", "csel1"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepdom", new String[]{"off", "on"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowdom", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("pi2").label("M8 2");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new int[]{55, 0, 0});
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("imp1(2)");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("pi1(2)", "pi2(2)");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("frame", "material");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("stress_area_factor", "(6.83/8)^2");
    model.param().descr("stress_area_factor", "\u87ba\u7eb9\u90e8\u5206\u7684\u521a\u5ea6\u6298\u51cf");
    model.param().set("sigma_p", "500[MPa]");
    model.param().descr("sigma_p", "\u87ba\u6813\u9884\u7d27\u529b");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u87ba\u7eb9\u8fb9\u754c");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"geom1_pi1_sel5", "geom1_pi2_sel5"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u5e94\u529b\u533a\u7f29\u51cf\u57df");
    model.component("comp1").selection("adj1").set("entitydim", 2);
    model.component("comp1").selection("adj1").set("outputdim", 3);
    model.component("comp1").selection("adj1").set("input", new String[]{"uni1"});
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u87ba\u7eb9\u87ba\u6813\u57df");
    model.component("comp1").selection("adj2").set("entitydim", 2);
    model.component("comp1").selection("adj2").set("outputdim", 3);
    model.component("comp1").selection("adj2").set("input", new String[]{"geom1_pi1_sel5", "geom1_pi2_sel5"});

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
    model.component("comp1").material().duplicate("mat2", "mat1");
    model.component("comp1").material("mat2").label("\u94a2\uff0c\u5e94\u529b\u533a\u7f29\u51cf");
    model.component("comp1").material("mat2").selection().named("adj1");
    model.component("comp1").material("mat2").propertyGroup("Enu")
         .set("E", new String[]{"200e9[Pa]*stress_area_factor"});

    model.component("comp1").pair("ap1").manualSelection(true);
    model.component("comp1").pair("ap1").type("Contact");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("src");
    model.component("comp1").selection("sel1").set(4, 16);

    model.component("comp1").pair("ap1").source().named("sel1");

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("dst");
    model.component("comp1").selection("sel2").set(28, 60);

    model.component("comp1").pair("ap1").destination().named("sel2");
    model.component("comp1").pair("ap1").mapping("initial");
    model.component("comp1").pair("ap1").searchTol("1e-2");
    model.component("comp1").pair("ap3").manualSelection(true);
    model.component("comp1").pair("ap3").type("Contact");
    model.component("comp1").pair("ap3").mapping("initial");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1, 3, 25);
    model.component("comp1").physics("solid").create("pblt1", "BoltPrestress", -1);
    model.component("comp1").physics("solid").feature("pblt1").set("preTensionType", "PreTensionStress");
    model.component("comp1").physics("solid").feature("pblt1").set("s_pre", "sigma_p");
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt1").selection().named("geom1_pi1_sel8");
    model.component("comp1").physics("solid").feature("pblt1").create("sblt2", "BoltSelection", 2);
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt2").selection().named("geom1_pi2_sel8");
    model.component("comp1").physics("solid").create("btc1", "PairBoltThreadContact", 2);
    model.component("comp1").physics("solid").feature("btc1").set("pairs", new String[]{"ap3"});
    model.component("comp1").physics("solid").feature("btc1").set("l", "1.25[mm]");
    model.component("comp1").physics("solid").feature("btc1").set("DirectionAdjst", true);
    model.component("comp1").physics("solid").feature("btc1").set("mu", 0.1);
    model.component("comp1").physics("solid").feature("btc1").set("cnt_orient", "Up");
    model.component("comp1").physics("solid").feature("btc1").create("stb1", "ContactStabilization", 2);
    model.component("comp1").physics("solid").feature("dcnt1").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp1").physics("solid").feature("dcnt1").set("tunedFor", "Speed");
    model.component("comp1").physics("solid").feature("dcnt1").create("stb1", "ContactStabilization", 2);
    model.component("comp1").physics("solid").feature("dcnt1").feature("stb1").set("method", "manual");

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_csel1_dom");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("adj1");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "1[mm]");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", "2[mm]");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subdtech", "auto");
    model.sol("sol1").runAll();

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
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", "1");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset1");
    model.result().evaluationGroup("eg1")
         .label("\u87ba\u6813\u529b: \u87ba\u6813\u9884\u7d27\u529b 1 (\u7814\u7a76 1) (solid)");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").label("Bolt_1");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.solid.pblt1.sblt1.F_bolt", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.solid.pblt1.sblt1.F_shear", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg1").create("gev2", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev2").label("Bolt_2");
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "comp1.solid.pblt1.sblt2.F_bolt", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "comp1.solid.pblt1.sblt2.F_shear", 1);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "GPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().named("adj2");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u73af\u5411\u5e94\u529b");
    model.result("pg2").create("slc1", "Slice");
    model.result("pg2").feature("slc1").set("expr", "solid.sy");
    model.result("pg2").feature("slc1").set("quickplane", "zx");
    model.result("pg2").feature("slc1").set("quickynumber", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("slc1").create("sel1", "Selection");
    model.result("pg2").feature("slc1").feature("sel1").selection().set(1, 2);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u87ba\u7eb9\u63a5\u89e6");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u8868\u9762\uff1a\u6cd5\u5411\u63a5\u89e6\u529b (GPa)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "solid.Fn_up");
    model.result("pg3").feature("surf1").set("descr", "\u6cd5\u5411\u63a5\u89e6\u529b");
    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"-solid.btc1.en_upX*solid.Fn_up", "v", "w"});
    model.result("pg3").feature("arws1").setIndex("expr", "-solid.btc1.en_upY*solid.Fn_up", 1);
    model.result("pg3").feature("arws1").setIndex("expr", "-solid.btc1.en_upZ*solid.Fn_up", 2);
    model.result("pg3").feature("arws1").set("arrowbase", "head");
    model.result("pg3").feature("arws1").set("placement", "elements");
    model.result("pg3").feature("arws1").set("inheritplot", "surf1");
    model.result("pg3").feature("arws1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("arws1").feature("col1").set("expr", "solid.Fn_up");
    model.result("pg3").run();
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").set("param", "xy");
    model.result().dataset("surf1").selection().named("sel2");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u76d6\u4e4b\u95f4\u7684\u63a5\u89e6\u538b\u529b");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "solid.Tn");
    model.result("pg4").feature("surf1").set("descr", "\u63a5\u89e6\u538b\u529b");
    model.result("pg4").feature("surf1").create("hght1", "Height");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();

    model.title("\u4e3b\u8f74\u627f\u76d6\u87ba\u6813\u7684\u9884\u5e94\u529b");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u9884\u5e94\u529b\u87ba\u6813\u5efa\u6a21\u3002\n\n\u87ba\u6813\u51e0\u4f55\u53d6\u81ea\u201c\u96f6\u4ef6\u5e93\u201d\u3002\n\n\u4e3a\u4e86\u8fdb\u884c\u6bd4\u8f83\uff0c\u5176\u4e2d\u4e00\u4e2a\u87ba\u6813\u4f7f\u7528\u87ba\u7eb9\u63a5\u89e6\u516c\u5f0f\u5efa\u6a21\uff0c\u53e6\u4e00\u4e2a\u87ba\u6813\u901a\u8fc7\u7eaf\u8fde\u7eed\u6027\u6761\u4ef6\u8fde\u63a5\u5230\u87ba\u6813\u5b54\u3002\u901a\u8fc7\u964d\u4f4e\u6750\u6599\u521a\u5ea6\u6765\u8003\u8651\u87ba\u6813\u87ba\u7eb9\u90e8\u5206\u7684\u521a\u5ea6\u9000\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("main_bearing_cap.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
