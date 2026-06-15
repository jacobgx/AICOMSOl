/*
 * double_gauss_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:16 by COMSOL 6.3.0.290. */
public class double_gauss_lens {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Lenses_Cameras_and_Telescopes");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().label("\u53c2\u6570 1\uff1a\u900f\u955c\u89c4\u683c\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2\uff1a\u901a\u7528");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("lambda", "550[nm]", "\u6807\u79f0\u771f\u7a7a\u6ce2\u957f");
    model.param("par2").set("theta_x", "0[deg]", "\u6807\u79f0 x \u89c6\u573a\u89d2");
    model.param("par2").set("theta_y", "0[deg]", "\u6807\u79f0 y \u89c6\u573a\u89d2");
    model.param("par2").set("N_ring", "18", "\u516d\u8fb9\u73af\u6570");
    model.param("par2").set("P_nom", "58.941[mm]", "\u6807\u79f0\u5165\u5c04\u5149\u77b3\u76f4\u5f84");
    model.param("par2").set("vx", "tan(theta_x)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param("par2").set("vy", "tan(theta_y)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param("par2").set("vz", "1", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.param("par2").set("z_stop", "Tc_1+T_1+Tc_2+T_2+Tc_3+T_3", "\u5149\u9611 z \u5750\u6807");
    model.param("par2")
         .set("z_image", "Tc_1+T_1+Tc_2+T_2+Tc_3+T_3+Tc_4+T_4+Tc_5+T_5+Tc_6+T_6+Tc_7+T_7", "\u50cf\u5e73\u9762 z \u5750\u6807");
    model.param("par2").set("P_fac1", "-1.15", "\u5149\u77b3\u504f\u79fb\u5e38\u6570 1");
    model.param("par2").set("P_fac2", "-0.60", "\u5149\u77b3\u504f\u79fb\u5e38\u6570 2");
    model.param("par2")
         .set("P_fac", "P_fac1+P_fac2*sin(sqrt(theta_x^2+theta_y^2))", "\u5149\u77b3\u504f\u79fb\u56e0\u5b50");
    model.param("par2").set("dx", "(dz+P_fac*z_stop)*tan(theta_x)", "\u5149\u77b3\u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.param("par2").set("dy", "(dz+P_fac*z_stop)*tan(theta_y)", "\u5149\u77b3\u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.param("par2").set("dz", "-1[mm]", "\u5149\u77b3\u4e2d\u5fc3\uff0cz \u5206\u91cf");

    model.component("comp1").geom("geom1").label("\u53cc\u9ad8\u65af\u900f\u955c");
    model.component("comp1").geom("geom1").insertFile("double_gauss_lens_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott \u70ed\u5149");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat1").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "\u5185\u90e8\u900f\u5c04\u7387\uff0c10\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").label("Ohara S-LAM 3 Glass");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcname", "Cp");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"20", "0.452"}, 
         {"30", "0.465"}, 
         {"40", "0.477"}, 
         {"50", "0.49"}, 
         {"60", "0.5"}, 
         {"70", "0.51"}, 
         {"80", "0.522"}, 
         {"90", "0.532"}, 
         {"100", "0.542"}, 
         {"110", "0.552"}, 
         {"120", "0.562"}, 
         {"130", "0.572"}, 
         {"140", "0.58"}, 
         {"150", "0.588"}, 
         {"160", "0.594"}, 
         {"170", "0.598"}, 
         {"180", "0.604"}, 
         {"190", "0.608"}, 
         {"200", "0.612"}, 
         {"210", "0.615"}, 
         {"220", "0.62"}, 
         {"230", "0.623"}, 
         {"240", "0.627"}, 
         {"250", "0.631"}, 
         {"260", "0.635"}, 
         {"270", "0.639"}, 
         {"280", "0.642"}, 
         {"290", "0.646"}, 
         {"300", "0.648"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"J/(g*K)"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{"degC"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "4.25[g/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.655[W/(m*K)]", "0", "0", "0", "0.655[W/(m*K)]", "0", "0", "0", "0.655[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"80.0E-7[1/K]", "0", "0", "0", "80.0E-7[1/K]", "0", "0", "0", "80.0E-7[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.64258713E+00", "2.39634610E-01", "1.22483026E+00", "8.68246020E-03", "3.51226242E-02", "1.16604369E+02"});
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "25[degC]");
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat1").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"-4.66E-6", "9.34E-9", "-3.89E-11", "4.57E-7", "5.81E-10", "0.266"});
    model.component("comp1").material("mat1").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "25[degC]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "868.0E8[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.294");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"340", "0.1"}, 
         {"350", "0.41"}, 
         {"360", "0.69"}, 
         {"370", "0.83"}, 
         {"380", "0.916"}, 
         {"390", "0.951"}, 
         {"400", "0.968"}, 
         {"420", "0.982"}, 
         {"440", "0.987"}, 
         {"460", "0.99"}, 
         {"480", "0.993"}, 
         {"500", "0.995"}, 
         {"550", "0.997"}, 
         {"600", "0.996"}, 
         {"650", "0.996"}, 
         {"700", "0.997"}, 
         {"800", "0.999"}, 
         {"900", "0.997"}, 
         {"1000", "0.997"}, 
         {"1200", "0.996"}, 
         {"1400", "0.994"}, 
         {"1600", "0.992"}, 
         {"1800", "0.983"}, 
         {"2000", "0.966"}, 
         {"2200", "0.92"}, 
         {"2400", "0.77"}});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott \u70ed\u5149");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat2").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "\u5185\u90e8\u900f\u5c04\u7387\uff0c10\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").label("Ohara S-BAH11 Glass");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "3.59[g/cm^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.858[W/(m*K)]", "0", "0", "0", "0.858[W/(m*K)]", "0", "0", "0", "0.858[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"69.0E-7[1/K]", "0", "0", "0", "69.0E-7[1/K]", "0", "0", "0", "69.0E-7[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.57138860E+00", "1.47869313E-01", "1.28092846E+00", "9.10807936E-03", "4.02401684E-02", "1.30399367E+02"});
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "25[degC]");
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat2").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"2.98E-6", "1.09E-8", "-3.44E-11", "5.52E-7", "7.15E-10", "0.244"});
    model.component("comp1").material("mat2").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "25[degC]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "929.0E8[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.274");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"340", "0.04"}, 
         {"350", "0.27"}, 
         {"360", "0.56"}, 
         {"370", "0.75"}, 
         {"380", "0.86"}, 
         {"390", "0.922"}, 
         {"400", "0.952"}, 
         {"420", "0.975"}, 
         {"440", "0.982"}, 
         {"460", "0.987"}, 
         {"480", "0.991"}, 
         {"500", "0.994"}, 
         {"550", "0.997"}, 
         {"600", "0.995"}, 
         {"650", "0.995"}, 
         {"700", "0.996"}, 
         {"800", "0.997"}, 
         {"900", "0.997"}, 
         {"1000", "0.997"}, 
         {"1200", "0.998"}, 
         {"1400", "0.994"}, 
         {"1600", "0.995"}, 
         {"1800", "0.988"}, 
         {"2000", "0.976"}, 
         {"2200", "0.936"}, 
         {"2400", "0.84"}});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott \u70ed\u5149");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat3").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "\u5185\u90e8\u900f\u5c04\u7387\uff0c10\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "\u5185\u90e8\u900f\u5c04\u7387\uff0c25\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").label("Schott N-SF5 Glass");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2.86[g/cm^3]");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "0.77[J/(g*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.0[W/(m*K)]", "0", "0", "0", "1.0[W/(m*K)]", "0", "0", "0", "1.0[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.94E-6[1/K]", "0", "0", "0", "7.94E-6[1/K]", "0", "0", "0", "7.94E-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.52481889E+00", "1.87085527E-01", "1.42729015E+00", "1.12547560E-02", "5.88995392E-02", "1.29141675E+02"});
    model.component("comp1").material("mat3").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat3").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat3").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat3").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"-2.51E-7", "1.07E-8", "-2.4E-11", "7.85E-7", "1.15E-9", "0.278"});
    model.component("comp1").material("mat3").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "87.0[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.237");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2500", "0.758"}, 
         {"2325", "0.831"}, 
         {"1970", "0.95"}, 
         {"1530", "0.99"}, 
         {"1060", "0.998"}, 
         {"700", "0.996"}, 
         {"660", "0.995"}, 
         {"620", "0.995"}, 
         {"580", "0.996"}, 
         {"546", "0.995"}, 
         {"500", "0.99"}, 
         {"460", "0.982"}, 
         {"436", "0.973"}, 
         {"420", "0.963"}, 
         {"405", "0.928"}, 
         {"400", "0.905"}, 
         {"390", "0.826"}, 
         {"380", "0.642"}, 
         {"370", "0.276"}, 
         {"365", "0.116"}});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("funcname", "taui25");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("table", new String[][]{{"2500", "0.5"}, 
         {"2325", "0.63"}, 
         {"1970", "0.88"}, 
         {"1530", "0.975"}, 
         {"1060", "0.994"}, 
         {"700", "0.989"}, 
         {"660", "0.987"}, 
         {"620", "0.988"}, 
         {"580", "0.991"}, 
         {"546", "0.988"}, 
         {"500", "0.976"}, 
         {"460", "0.956"}, 
         {"436", "0.935"}, 
         {"420", "0.91"}, 
         {"405", "0.83"}, 
         {"400", "0.78"}, 
         {"390", "0.62"}, 
         {"380", "0.33"}, 
         {"370", "0.04"}});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25")
         .set("taui25", "taui25(c_const/freq)");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").addInput("frequency");
    model.component("comp1").material("mat1").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat3").selection().named("geom1_csel3_dom");

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("ExteriorUnmeshedProperties")
         .setIndex("DispersionModel", "AirEdlen1953", 0);
    model.component("comp1").physics("gop").prop("ComputeOpticalPathLength")
         .setIndex("ComputeOpticalPathLength", 1, 0);
    model.component("comp1").physics("gop").feature("mp1")
         .set("RefractiveIndexDomains", "GetDispersionModelFromMaterial");
    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").feature("op1").set("lambda0", "lambda");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("GridType", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").set("qcc", new String[]{"dx", "dy", "dz"});
    model.component("comp1").physics("gop").feature("relg1").set("rcc", new String[]{"nix", "niy", "niz"});
    model.component("comp1").physics("gop").feature("relg1").set("Rc", "P_nom/2");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Ncr", "N_ring", 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new String[]{"vx", "vy", "vz"});
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_csel6_bnd");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("\u5149\u9611");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_csel7_bnd");
    model.component("comp1").physics("gop").feature("wall2").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall3", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall3").label("\u50cf\u9762");
    model.component("comp1").physics("gop").feature("wall3").selection().named("geom1_csel8_bnd");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("lunit", "mm");
    model.study("std1").feature("rtrac").set("llist", "0 200");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.L");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result("pg1").run();
    model.result("pg1").label("\u5c04\u7ebf\u56fe 1");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").set("legendpos", "bottom");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("filt1").set("type", "logical");
    model.result("pg1").feature("rtrj1").feature("filt1").set("logicalexpr", "at(0,abs(gop.deltaqx) < 0.1[mm])");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "cpl1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("data", "cpl1");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u5c04\u7ebf\u56fe 2");
    model.result("pg2").set("data", "ray1");
    model.result("pg2").set("view", "new");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("rtrj1", "RayTrajectories");
    model.result("pg2").feature("rtrj1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("rtrj1").feature("col1").set("expr", "at('last',gop.rrel)");
    model.result("pg2").feature("rtrj1").feature("col1").set("unit", "\u00b5m");
    model.result("pg2").feature("rtrj1").feature("col1").set("colortable", "Viridis");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "gop.nrefd");
    model.result("pg2").feature("surf1").set("descr", "\u6298\u5c04\u7387\uff0cd \u7ebf");
    model.result("pg2").feature("surf1").set("colortable", "GrayScale");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("geom1_csel4_bnd");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u70b9\u5217\u56fe");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").set("legendpos", "bottom");
    model.result("pg3").create("spot1", "SpotDiagram");
    model.result("pg3").feature("spot1").set("spotcoordsactive", true);
    model.result("pg3").feature("spot1").set("spotcoordssystem", "global");
    model.result("pg3").feature("spot1").set("spotcoordsprecision", 6);
    model.result("pg3").feature("spot1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("spot1").feature("col1").set("expr", "at(0,gop.rrel)");
    model.result("pg3").feature("spot1").feature("col1").set("colortable", "Viridis");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("spot2", "spot1");
    model.result("pg3").run();
    model.result("pg3").feature("spot2").set("normal", "userdefined");
    model.result().dataset().create("ip1", "IntersectionPoint3D");
    model.result("pg3").feature("spot2").set("data", "ip1");
    model.result().dataset("ip1").set("data", "ray1");
    model.result().dataset("ip1").set("genmethod", "pointnormal");
    model.result().dataset("ip1").setIndex("genpnpoint", "6.457198456136526E-7[mm]", 0);
    model.result().dataset("ip1").setIndex("genpnpoint", "-1.1369854084206811E-6[mm]", 1);
    model.result().dataset("ip1").setIndex("genpnpoint", "136.92790155259385[mm]", 2);
    model.result().dataset("ip1").set("genpnvec", new double[]{0, 0, 1});
    model.result("pg3").feature("spot2").run();
    model.result().dataset("ip1").set("genmethod", "pointnormal");
    model.result().dataset("ip1").setIndex("genpnpoint", "6.437728821286592E-7[mm]", 0);
    model.result().dataset("ip1").setIndex("genpnpoint", "-1.1340705664604757E-6[mm]", 1);
    model.result().dataset("ip1").setIndex("genpnpoint", "136.92790141774762[mm]", 2);
    model.result().dataset("ip1").set("genpnvec", new double[]{0, 0, 1});
    model.result("pg3").feature("spot2").run();
    model.result("pg3").set("ispendingzoom", true);
    model.result("pg3").feature("spot2").set("posexpr", new String[]{"0.25", "0"});
    model.result("pg3").feature("spot2").set("inheritplot", "spot1");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u5149\u5b66\u50cf\u5dee\u56fe");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("legendpos", "bottom");
    model.result("pg4").create("oab1", "OpticalAberration");
    model.result("pg4").feature("oab1").set("normal", "userdefined");
    model.result().dataset().create("ip2", "IntersectionPoint3D");
    model.result("pg4").feature("oab1").set("data", "ip2");
    model.result().dataset("ip2").set("data", "ray1");
    model.result().dataset("ip2").set("type", "hemisphere");
    model.result().dataset("ip2").setIndex("center", "6.457198456136509E-7[mm]", 0);
    model.result().dataset("ip2").setIndex("axis", "-0.0", 0);
    model.result().dataset("ip2").setIndex("center", "-1.136985408421156E-6[mm]", 1);
    model.result().dataset("ip2").setIndex("axis", "-0.0", 1);
    model.result().dataset("ip2").setIndex("center", "136.92790155259385[mm]", 2);
    model.result().dataset("ip2").setIndex("axis", "-1.0", 2);
    model.result().dataset("ip2").set("radius", "50[mm]");
    model.result("pg4").feature("oab1").run();
    model.result("pg4").feature("oab1").set("colortable", "Dipole");
    model.result("pg4").feature("oab1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature().duplicate("oab2", "oab1");
    model.result("pg4").run();
    model.result("pg4").feature("oab2").set("terms", "selectindividual");
    model.result("pg4").feature("oab2").set("z00", true);
    model.result("pg4").feature("oab2").set("z1m1", true);
    model.result("pg4").feature("oab2").set("z11", true);
    model.result("pg4").feature("oab2").set("z2m2", true);
    model.result("pg4").feature("oab2").set("z20", true);
    model.result("pg4").feature("oab2").set("z22", true);
    model.result("pg4").feature("oab2").set("z3m3", true);
    model.result("pg4").feature("oab2").set("z3m1", true);
    model.result("pg4").feature("oab2").set("z31", true);
    model.result("pg4").feature("oab2").set("z33", true);
    model.result("pg4").feature("oab2").set("z4m4", true);
    model.result("pg4").feature("oab2").set("z4m2", true);
    model.result("pg4").feature("oab2").set("z40", true);
    model.result("pg4").feature("oab2").set("z42", true);
    model.result("pg4").feature("oab2").set("z44", true);
    model.result("pg4").feature("oab2").set("z5m5", true);
    model.result("pg4").feature("oab2").set("z5m3", true);
    model.result("pg4").feature("oab2").set("z5m1", true);
    model.result("pg4").feature("oab2").set("z51", true);
    model.result("pg4").feature("oab2").set("z53", true);
    model.result("pg4").feature("oab2").set("z55", true);
    model.result("pg4").feature("oab2").set("z00", false);
    model.result("pg4").feature("oab2").set("z20", false);
    model.result("pg4").feature("oab2").set("posexpr", new String[]{"2.5", "0"});
    model.result("pg4").feature("oab2").set("inheritplot", "oab1");
    model.result("pg4").run();

    model.title("\u53cc\u9ad8\u65af\u900f\u955c");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u8bbe\u7f6e\u591a\u5143\u7269\u955c\u3002\u9009\u7528\u7684\u900f\u955c\u4e3a W. Smith \u5728 'Modern Lens Design'\uff082005 \u5e74\u7b2c\u4e8c\u7248\u7b2c 323 \u9875\uff09\u4e2d\u63cf\u8ff0\u7684\u53cc\u9ad8\u65af\u900f\u955c\u3002\n\n\u6559\u7a0b\u6f14\u793a\u4e86\u5982\u4f55\u4f7f\u7528\u201c\u5c04\u7ebf\u5149\u5b66\u6a21\u5757\u201d\u96f6\u4ef6\u5e93\u4e2d\u7684\u201c\u7403\u9762\u900f\u955c\uff08\u4e09\u7ef4\uff09\u201d\u548c\u201c\u5706\u5f62\u5e73\u9762\u73af\u201d\u96f6\u4ef6\u6765\u521b\u5efa\u51e0\u4f55\u5e8f\u5217\u3002\n\n\u672c\u4f8b\u5728\u5355\u4e00\u6ce2\u957f\u548c\u89c6\u573a\u89d2\u4e0b\u8fdb\u884c\u5149\u7ebf\u8ffd\u8e2a\uff0c\u5e76\u540c\u6b65\u5c55\u793a\u4e86\u5c04\u7ebf\u56fe\u3001\u70b9\u5217\u56fe\u548c\u5149\u5b66\u50cf\u5dee\u56fe\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("double_gauss_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
