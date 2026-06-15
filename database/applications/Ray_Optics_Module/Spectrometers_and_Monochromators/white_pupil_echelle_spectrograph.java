/*
 * white_pupil_echelle_spectrograph.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:21 by COMSOL 6.3.0.290. */
public class white_pupil_echelle_spectrograph {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Spectrometers_and_Monochromators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().label("\u53c2\u6570 1\uff1a\u51e0\u4f55");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2\uff1a\u901a\u7528");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("N_hex", "10", "\u516d\u8fb9\u73af\u6570");
    model.param("par2").set("xi", "sin(theta_i)", "\u8f93\u5165\u5149\u8f74\uff0cx \u5206\u91cf");
    model.param("par2").set("yi", "0", "\u8f93\u5165\u5149\u8f74\uff0cy \u5206\u91cf");
    model.param("par2").set("zi", "cos(theta_i)", "\u8f93\u5165\u5149\u8f74\uff0cz \u5206\u91cf");
    model.param("par2").set("F", "f_1/B", "\u8f93\u5165\u7126\u6bd4");
    model.param("par2").set("NA", "0.5/F", "\u8f93\u5165\u6570\u503c\u5b54\u5f84");
    model.param("par2").set("T_ech", "31.6[mm^-1]", "\u9636\u68af\u5149\u6805\u7ebf\u9891\u7387");
    model.param("par2").set("sigma_ech", "1/T_ech", "\u9636\u68af\u5149\u6805\u7ebf\u95f4\u8ddd");
    model.param("par2")
         .set("mlam", "2*sigma_ech*cos(gamma_ech)*sin(theta_B)", "\u7ea7\u6570\u4e58\u4ee5\u6ce2\u957f\u5e38\u6570");
    model.param("par2").set("lam_min", "450[nm]", "\u6700\u5c0f\u6807\u79f0\u6ce2\u957f");
    model.param("par2").set("lam_max", "600[nm]", "\u6700\u5927\u6807\u79f0\u6ce2\u957f");
    model.param("par2").set("m_max", "round(mlam/lam_min)", "\u6700\u5927\u7ea7\u6570");
    model.param("par2").set("m_min", "round(mlam/lam_max)", "\u6700\u5c0f\u7ea7\u6570");
    model.param("par2").set("m_mid", "(m_max+m_min)/2-2", "\u4e2d\u95f4\u7ea7\u6570");

    model.component("comp1").geom("geom1")
         .label("\u767d\u77b3\u9636\u68af\u5149\u6805\u5149\u8c31\u4eea\u51e0\u4f55\u5e8f\u5217");
    model.component("comp1").geom("geom1").insertFile("white_pupil_echelle_spectrograph_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
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
    model.component("comp1").material("mat1").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "\u5185\u90e8\u900f\u5c04\u7387\uff0c25\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").label("Schott N-BK7 Glass");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2.51[g/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "0.858[J/(g*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.114[W/(m*K)]", "0", "0", "0", "1.114[W/(m*K)]", "0", "0", "0", "1.114[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.1E-6[1/K]", "0", "0", "0", "7.1E-6[1/K]", "0", "0", "0", "7.1E-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.03961212E+00", "2.31792344E-01", "1.01046945E+00", "6.00069867E-03", "2.00179144E-02", "1.03560653E+02"});
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat1").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"1.86E-6", "1.31E-8", "-1.37E-11", "4.34E-7", "6.27E-10", "0.17"});
    model.component("comp1").material("mat1").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "82.0[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.206");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2500", "0.665"}, 
         {"2325", "0.793"}, 
         {"1970", "0.933"}, 
         {"1530", "0.992"}, 
         {"1060", "0.999"}, 
         {"700", "0.998"}, 
         {"660", "0.998"}, 
         {"620", "0.998"}, 
         {"580", "0.998"}, 
         {"546", "0.998"}, 
         {"500", "0.998"}, 
         {"460", "0.997"}, 
         {"436", "0.997"}, 
         {"420", "0.997"}, 
         {"405", "0.997"}, 
         {"400", "0.997"}, 
         {"390", "0.996"}, 
         {"380", "0.993"}, 
         {"370", "0.991"}, 
         {"365", "0.988"}, 
         {"350", "0.967"}, 
         {"334", "0.905"}, 
         {"320", "0.77"}, 
         {"310", "0.574"}, 
         {"300", "0.292"}, 
         {"290", "0.063"}});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("funcname", "taui25");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("table", new String[][]{{"2500", "0.36"}, 
         {"2325", "0.56"}, 
         {"1970", "0.84"}, 
         {"1530", "0.98"}, 
         {"1060", "0.997"}, 
         {"700", "0.996"}, 
         {"660", "0.994"}, 
         {"620", "0.994"}, 
         {"580", "0.995"}, 
         {"546", "0.996"}, 
         {"500", "0.994"}, 
         {"460", "0.993"}, 
         {"436", "0.992"}, 
         {"420", "0.993"}, 
         {"405", "0.993"}, 
         {"400", "0.992"}, 
         {"390", "0.989"}, 
         {"380", "0.983"}, 
         {"370", "0.977"}, 
         {"365", "0.971"}, 
         {"350", "0.92"}, 
         {"334", "0.78"}, 
         {"320", "0.52"}, 
         {"310", "0.25"}, 
         {"300", "0.05"}});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25")
         .set("taui25", "taui25(c_const/freq)");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").addInput("frequency");
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
    model.component("comp1").material("mat2").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "\u5185\u90e8\u900f\u5c04\u7387\uff0c25\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").label("Schott N-KZFS5 Glass");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "3.04[g/cm^3]");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "0.73[J/(g*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.95[W/(m*K)]", "0", "0", "0", "0.95[W/(m*K)]", "0", "0", "0", "0.95[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"6.38E-6[1/K]", "0", "0", "0", "6.38E-6[1/K]", "0", "0", "0", "6.38E-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.47460789E+00", "1.93584488E-01", "1.26589974E+00", "9.86143816E-03", "4.45477583E-02", "1.06436258E+02"});
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat2").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"4.54E-6", "1.19E-8", "2.93E-12", "6.89E-7", "8.6E-10", "0.23"});
    model.component("comp1").material("mat2").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "89.0[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.243");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2500", "0.657"}, 
         {"2325", "0.826"}, 
         {"1970", "0.963"}, 
         {"1530", "0.988"}, 
         {"1060", "0.999"}, 
         {"700", "0.998"}, 
         {"660", "0.997"}, 
         {"620", "0.997"}, 
         {"580", "0.997"}, 
         {"546", "0.997"}, 
         {"500", "0.994"}, 
         {"460", "0.99"}, 
         {"436", "0.986"}, 
         {"420", "0.983"}, 
         {"405", "0.978"}, 
         {"400", "0.976"}, 
         {"390", "0.967"}, 
         {"380", "0.95"}, 
         {"370", "0.928"}, 
         {"365", "0.91"}, 
         {"350", "0.793"}, 
         {"334", "0.372"}, 
         {"320", "0.017"}});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("funcname", "taui25");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("table", new String[][]{{"2500", "0.35"}, 
         {"2325", "0.62"}, 
         {"1970", "0.91"}, 
         {"1530", "0.97"}, 
         {"1060", "0.998"}, 
         {"700", "0.994"}, 
         {"660", "0.992"}, 
         {"620", "0.992"}, 
         {"580", "0.993"}, 
         {"546", "0.992"}, 
         {"500", "0.985"}, 
         {"460", "0.974"}, 
         {"436", "0.965"}, 
         {"420", "0.958"}, 
         {"405", "0.946"}, 
         {"400", "0.94"}, 
         {"390", "0.92"}, 
         {"380", "0.88"}, 
         {"370", "0.83"}, 
         {"365", "0.79"}, 
         {"350", "0.56"}, 
         {"334", "0.08"}});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25")
         .set("taui25", "taui25(c_const/freq)");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").addInput("frequency");
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
    model.component("comp1").material("mat3").label("Schott N-SK2 Glass");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "3.55[g/cm^3]");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "0.595[J/(g*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.776[W/(m*K)]", "0", "0", "0", "0.776[W/(m*K)]", "0", "0", "0", "0.776[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"6.0E-6[1/K]", "0", "0", "0", "6.0E-6[1/K]", "0", "0", "0", "6.0E-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.28189012E+00", "2.57738258E-01", "9.68186040E-01", "7.27191640E-03", "2.42823527E-02", "1.10377773E+02"});
    model.component("comp1").material("mat3").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat3").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat3").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat3").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"3.8E-6", "1.41E-8", "2.28E-11", "6.44E-7", "8.03E-11", "0.108"});
    model.component("comp1").material("mat3").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "78.0[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.263");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2500", "0.815"}, 
         {"2325", "0.896"}, 
         {"1970", "0.971"}, 
         {"1530", "0.995"}, 
         {"1060", "0.998"}, 
         {"700", "0.998"}, 
         {"660", "0.998"}, 
         {"620", "0.998"}, 
         {"580", "0.998"}, 
         {"546", "0.998"}, 
         {"500", "0.996"}, 
         {"460", "0.993"}, 
         {"436", "0.993"}, 
         {"420", "0.994"}, 
         {"405", "0.994"}, 
         {"400", "0.994"}, 
         {"390", "0.992"}, 
         {"380", "0.988"}, 
         {"370", "0.976"}, 
         {"365", "0.967"}, 
         {"350", "0.905"}, 
         {"334", "0.752"}, 
         {"320", "0.504"}, 
         {"310", "0.276"}, 
         {"300", "0.102"}, 
         {"290", "0.02"}});
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
         .set("table", new String[][]{{"2500", "0.6"}, 
         {"2325", "0.76"}, 
         {"1970", "0.93"}, 
         {"1530", "0.988"}, 
         {"1060", "0.995"}, 
         {"700", "0.995"}, 
         {"660", "0.994"}, 
         {"620", "0.994"}, 
         {"580", "0.995"}, 
         {"546", "0.995"}, 
         {"500", "0.99"}, 
         {"460", "0.983"}, 
         {"436", "0.982"}, 
         {"420", "0.984"}, 
         {"405", "0.985"}, 
         {"400", "0.984"}, 
         {"390", "0.979"}, 
         {"380", "0.97"}, 
         {"370", "0.94"}, 
         {"365", "0.92"}, 
         {"350", "0.78"}, 
         {"334", "0.49"}, 
         {"320", "0.18"}, 
         {"310", "0.04"}});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25")
         .set("taui25", "taui25(c_const/freq)");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").addInput("frequency");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat4").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott \u70ed\u5149");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat4").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "\u5185\u90e8\u900f\u5c04\u7387\uff0c10\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "\u5185\u90e8\u900f\u5c04\u7387\uff0c25\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat4").label("Schott N-SF5 Glass");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2.86[g/cm^3]");
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "0.77[J/(g*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.0[W/(m*K)]", "0", "0", "0", "1.0[W/(m*K)]", "0", "0", "0", "1.0[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.94E-6[1/K]", "0", "0", "0", "7.94E-6[1/K]", "0", "0", "0", "7.94E-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.52481889E+00", "1.87085527E-01", "1.42729015E+00", "1.12547560E-02", "5.88995392E-02", "1.29141675E+02"});
    model.component("comp1").material("mat4").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat4").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat4").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat4").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"-2.51E-7", "1.07E-8", "-2.4E-11", "7.85E-7", "1.15E-9", "0.278"});
    model.component("comp1").material("mat4").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "87.0[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.237");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func("int1")
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
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").func("int1")
         .set("funcname", "taui25");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").func("int1")
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
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25")
         .set("taui25", "taui25(c_const/freq)");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").addInput("frequency");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat5").label("Silica glass");
    model.component("comp1").material("mat5").set("family", "custom");
    model.component("comp1").material("mat5").set("diffuse", "custom");
    model.component("comp1").material("mat5").set("ambient", "custom");
    model.component("comp1").material("mat5").set("noise", true);
    model.component("comp1").material("mat5").set("fresnel", 0.99);
    model.component("comp1").material("mat5").set("roughness", 0.02);
    model.component("comp1").material("mat5").set("diffusewrap", 0);
    model.component("comp1").material("mat5").set("reflectance", 0);
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat5").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat5").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});
    model.component("comp1").material("mat1").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat3").selection().named("geom1_csel3_dom");
    model.component("comp1").material("mat4").selection().named("geom1_csel4_dom");
    model.component("comp1").material("mat5").selection().named("geom1_csel12_dom");

    model.component("comp1").physics("gop").selection().named("geom1_unisel1");
    model.component("comp1").physics("gop").prop("WavelengthDistribution")
         .setIndex("WavelengthDistribution", "PolychromaticWavelength", 0);
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("ExteriorUnmeshedProperties")
         .setIndex("DispersionModel", "AbsoluteVacuum", 0);
    model.component("comp1").physics("gop").prop("ComputeOpticalPathLength")
         .setIndex("ComputeOpticalPathLength", 1, 0);
    model.component("comp1").physics("gop").prop("CountReflections").setIndex("CountReflections", 1, 0);
    model.component("comp1").physics("gop").prop("StoreRayStatusData").setIndex("StoreRayStatusData", 1, 0);
    model.component("comp1").physics("gop").feature("mp1")
         .set("RefractiveIndexDomains", "GetDispersionModelFromMaterial");
    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").create("rpt1", "ReleaseFromPoint", 0);
    model.component("comp1").physics("gop").feature("rpt1")
         .label("\u4ece\u5165\u5c04\u72ed\u7f1d\u91ca\u653e\uff1am_max");
    model.component("comp1").physics("gop").feature("rpt1").selection().named("geom1_pt1_pnt");
    model.component("comp1").physics("gop").feature("rpt1").set("RayDirectionVector", "Conical");
    model.component("comp1").physics("gop").feature("rpt1").set("ConicalDistribution", "Hexapolar");
    model.component("comp1").physics("gop").feature("rpt1").setIndex("Nctheta", "N_hex", 0);
    model.component("comp1").physics("gop").feature("rpt1").set("cax", new String[]{"xi", "yi", "zi"});
    model.component("comp1").physics("gop").feature("rpt1").set("alphac", "atan(NA)");
    model.component("comp1").physics("gop").feature("rpt1").set("LDistributionFunction", "ListOfValues");
    model.component("comp1").physics("gop").feature("rpt1")
         .setIndex("lambda0vals", "mlam/(m_max+0.499) mlam/(m_max+0.25) mlam/m_max mlam/(m_max-0.25) mlam/(m_max-0.499)", 0);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("gop").feature().duplicate("rpt2", "rpt1");
    model.component("comp1").physics("gop").feature("rpt2")
         .label("\u4ece\u5165\u5c04\u72ed\u7f1d\u91ca\u653e\uff1am_mid");
    model.component("comp1").physics("gop").feature("rpt2")
         .setIndex("lambda0vals", "mlam/(m_mid+0.499) mlam/(m_mid+0.25) mlam/m_mid mlam/(m_mid-0.25) mlam/(m_mid-0.499)", 0);
    model.component("comp1").physics("gop").feature().duplicate("rpt3", "rpt2");
    model.component("comp1").physics("gop").feature("rpt3")
         .label("\u4ece\u5165\u5c04\u72ed\u7f1d\u91ca\u653e\uff1am_min");
    model.component("comp1").physics("gop").feature("rpt3")
         .setIndex("lambda0vals", "mlam/(m_min+0.495) mlam/(m_min+0.25) mlam/m_min mlam/(m_min-0.25) mlam/(m_min-0.495)", 0);
    model.component("comp1").physics("gop").create("mir1", "Mirror", 2);
    model.component("comp1").physics("gop").feature("mir1").label("\u53cd\u5c04\u955c");
    model.component("comp1").physics("gop").feature("mir1").selection().named("geom1_csel10_bnd");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1")
         .label("\u969c\u788d\u7269\uff08\u5149\u6805\u548c\u53cd\u5c04\u955c\uff09");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_csel11_bnd");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("\u969c\u788d\u7269\uff08\u76f8\u673a\uff09");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_csel7_bnd");
    model.component("comp1").physics("gop").feature("wall2").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("grat1", "Grating", 2);
    model.component("comp1").physics("gop").feature("grat1").label("\u9636\u68af\u5149\u6805");
    model.component("comp1").physics("gop").feature("grat1").selection().named("geom1_pi3_sel3");
    model.component("comp1").physics("gop").feature("grat1")
         .set("DirectionOfPeriodicity", "ParallelToReferenceEdge");
    model.component("comp1").physics("gop").feature("grat1").selection("ReferenceEdge").set(182);
    model.component("comp1").physics("gop").feature("grat1").set("UseRelativeOrderNumbers", true);
    model.component("comp1").physics("gop").feature("grat1").set("RaysToRelease", "Reflected");
    model.component("comp1").physics("gop").feature("grat1").set("dg", "sigma_ech");
    model.component("comp1").physics("gop").feature("grat1").set("thetab", "theta_B");
    model.component("comp1").physics("gop").create("grat2", "Grating", 2);
    model.component("comp1").physics("gop").feature("grat2").label("\u4ea4\u53c9\u8272\u6563\u5149\u6805");
    model.component("comp1").physics("gop").feature("grat2").selection().named("geom1_pi4_sel3");
    model.component("comp1").physics("gop").feature("grat2")
         .set("DirectionOfPeriodicity", "ParallelToReferenceEdge");
    model.component("comp1").physics("gop").feature("grat2").selection("ReferenceEdge").set(117);
    model.component("comp1").physics("gop").feature("grat2").set("RaysToRelease", "Transmitted");
    model.component("comp1").physics("gop").feature("grat2").set("dg", "sigma_xdp");
    model.component("comp1").physics("gop").feature("grat2").feature("dfo1").set("mg", 1);
    model.component("comp1").physics("gop").create("wall3", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall3").label("\u63a2\u6d4b\u5668");
    model.component("comp1").physics("gop").feature("wall3").selection().named("geom1_pi12_sel1");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_csel5_bnd", "geom1_pi4_sel2", "geom1_pi5_sel2"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").set("entitydim", 2);
    model.component("comp1").selection("uni2").set("input", new String[]{"geom1_csel10_bnd", "geom1_pi3_sel3"});

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("lunit", "mm");
    model.study("std1").feature("rtrac").set("llist", "0 5000");
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
    model.result("pg1").run();
    model.result("pg1").label("\u5c04\u7ebf\u56fe");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("legendpos", "bottom");
    model.result("pg1").set("legendactive", true);
    model.result("pg1").set("legendprecision", 4);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.lambda0");
    model.result("pg1").feature("rtrj1").feature("col1").set("unit", "nm");
    model.result("pg1").feature("rtrj1").feature("col1").set("colortable", "Spectrum");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("filt1").set("type", "logical");
    model.result("pg1").feature("rtrj1").feature("filt1").set("logicalexpr", "at(0,tan(gop.phic))>0.95*NA");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_csel11_bnd");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("color", "blue");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("sel1").selection().named("uni1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("tran1").set("transparency", 0.8);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf3", "surf2");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("color", "custom");
    model.result("pg1").feature("surf3")
         .set("customcolor", new double[]{0.7411764860153198, 0.7882353067398071, 0.8470588326454163});
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("sel1").selection().named("uni2");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("tran1").set("transparency", 0.2);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u9636\u68af\u5149\u6805\u56fe");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").set("legendpos", "bottom");
    model.result("pg2").set("legendactive", true);
    model.result("pg2").set("legendprecision", 4);
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result("pg2").feature("spot1").set("numberofreflectionsactive", true);
    model.result("pg2").feature("spot1").set("numberofreflections", "4");
    model.result("pg2").feature("spot1").set("transverse", "userdefined");
    model.result("pg2").feature("spot1").set("transverseexpr", new int[]{0, 1, 0});
    model.result("pg2").feature("spot1").set("arrangement", "single");
    model.result("pg2").feature("spot1").set("spotsizeactive", false);
    model.result("pg2").feature("spot1").set("sphereradiusscaleactive", true);
    model.result("pg2").feature("spot1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").feature("col1").set("expr", "gop.lambda0");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u70b9\u5217\u56fe");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("legendpos", "bottom");
    model.result("pg3").set("legendactive", true);
    model.result("pg3").set("legendprecision", 4);
    model.result("pg3").create("spot1", "SpotDiagram");
    model.result("pg3").feature("spot1").set("numberofreflectionsactive", true);
    model.result("pg3").feature("spot1").set("numberofreflections", "4");
    model.result("pg3").feature("spot1").set("arrangement", "wavelength");
    model.result("pg3").feature("spot1").set("layout", "rectangular");
    model.result("pg3").feature("spot1").set("columns", 5);
    model.result("pg3").feature("spot1").set("tolerance", "0.1[nm]");
    model.result("pg3").feature("spot1").set("origin", "area");
    model.result("pg3").feature("spot1").set("wavelengthactive", true);
    model.result("pg3").feature("spot1").set("wavelengthprecision", 5);
    model.result("pg3").feature("spot1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("spot1").feature("col1").set("expr", "at(0,tan(gop.phic))");
    model.result("pg3").run();

    model.view("view15").set("showgrid", false);

    model.title("\u767d\u77b3\u9636\u68af\u5149\u6805\u5149\u8c31\u4eea");

    model
         .description("\u9636\u68af\u5149\u6805\u5149\u8c31\u4eea\u5e38\u7528\u4e8e\u5929\u6587\u5b66\u4e2d\u5bf9\u6052\u661f\u5927\u6c14\u8fdb\u884c\u9ad8\u5206\u8fa8\u7387\u5206\u6790\uff0c\u8fd8\u7528\u4e8e\u7cbe\u5bc6\u591a\u666e\u52d2\u6d4b\u901f\u4eea\u3002\u672c\u6559\u7a0b\u6a21\u62df\u6b64\u4eea\u5668\u7684\u201c\u767d\u77b3\u201d\u5f62\u5f0f\uff0c\u5229\u7528 COMSOL \u96f6\u4ef6\u5e93\u4e2d\u7684\u591a\u4e2a\u96f6\u4ef6\uff0c\u6f14\u793a\u4e86\u5982\u4f55\u521b\u5efa\u590d\u6742\u7684\u5168\u53c2\u6570\u5316\u51e0\u4f55\u7ed3\u6784\uff0c\u5176\u4e2d\u5305\u542b\u4e24\u4e2a\u5149\u6805\u7ec4\u4ef6\uff0c\u5e76\u6f14\u793a\u4e86\u5982\u4f55\u4f7f\u7528\u201c\u884d\u5c04\u7ea7\u201d\u8282\u70b9\u3002\u9636\u68af\u5149\u6805\u5728\u9ad8\u9636\u4e2d\u4f7f\u7528\uff0c\u800c\u4ea4\u53c9\u8272\u6563\u5149\u6805\u5728\u4e00\u9636\u4e2d\u4f7f\u7528\uff0c\u8fd8\u751f\u6210\u4e86\u7ed3\u679c\u5c04\u7ebf\u56fe\u3001\u9636\u68af\u5149\u6805\u56fe\u548c\u70b9\u5217\u56fe\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("white_pupil_echelle_spectrograph.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
