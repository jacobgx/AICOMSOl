/*
 * cross_grating_echelle_spectrograph.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:20 by COMSOL 6.3.0.290. */
public class cross_grating_echelle_spectrograph {

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
    model.param("par2").label("\u53c2\u6570 2\uff1a\u6a21\u578b");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("lam_nom", "525[nm]", "\u6807\u79f0\u6ce2\u957f");
    model.param("par2").set("N_hex", "10", "\u516d\u8fb9\u73af\u6570");
    model.param("par2").set("N_lam", "5", "\u6bcf\u7ea7\u7684\u6ce2\u957f\u6570");
    model.param("par2").set("f_col", "100.0[mm]", "\u51c6\u76f4\u7126\u8ddd");
    model.param("par2").set("D", "15.0[mm]", "\u51c6\u76f4\u5149\u675f\u76f4\u5f84");
    model.param("par2").set("F", "f_col/D", "\u8f93\u5165\u7126\u6bd4");
    model.param("par2").set("NA", "0.5/F", "\u8f93\u5165\u6570\u503c\u5b54\u5f84");
    model.param("par2").set("T_ech", "50[1/mm]", "\u9636\u68af\u5149\u6805\u7ebf\u5bc6\u5ea6");
    model.param("par2").set("sigma_ech", "1/T_ech", "\u9636\u68af\u5149\u6805\u7ebf\u5206\u79bb");
    model.param("par2")
         .set("mlam_const", "2*sigma_ech*cos(theta_xdp)*sin(theta_B)", "\u7ea7\u6570\u4e58\u4ee5\u6ce2\u957f\u5e38\u6570");
    model.param("par2").set("m", "round(mlam_const/lam_nom)", "\u9636\u68af\u5149\u6805\u884d\u5c04\u7ea7");
    model.param("par2").set("lam_B", "mlam_const/m", "\u5b9e\u9645\u9636\u68af\u5149\u6805\u95ea\u8000\u6ce2\u957f");
    model.param("par2").set("dlam_FSR", "lam_B/m", "\u9636\u68af\u5149\u6805\u81ea\u7531\u5149\u8c31\u8303\u56f4");
    model.param("par2").set("lam_min", "lam_B-dlam_FSR/2", "\u6700\u5c0f\u6ce2\u957f\uff08m_nom \u7ea7\uff09");
    model.param("par2").set("lam_max", "lam_B+dlam_FSR/2", "\u6700\u5927\u6ce2\u957f\uff08m_nom \u7ea7\uff09");
    model.param("par2").set("lam_step", "(lam_max-lam_min)/(N_lam-1)", "\u6ce2\u957f\u6b65\u957f");

    model.component("comp1").geom("geom1")
         .label("\u4ea4\u53c9\u5149\u6805\u9636\u68af\u5149\u6805\u5149\u8c31\u4eea\u51e0\u4f55\u5e8f\u5217");
    model.component("comp1").geom("geom1")
         .insertFile("cross_grating_echelle_spectrograph_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

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
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat5").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott \u70ed\u5149");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat5").propertyGroup()
         .create("InternalTransmittance5", "InternalTransmittance5", "\u5185\u90e8\u900f\u5c04\u7387\uff0c5\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance5").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "\u5185\u90e8\u900f\u5c04\u7387\uff0c10\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat5").label("CDGM H-ZF39 Glass");
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "2.92[g/cm^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"90.0E-7[1/K]", "0", "0", "0", "90.0E-7[1/K]", "0", "0", "0", "90.0E-7[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.53633529E-01", "1.54070508E+00", "1.20956745E+00", "6.03806210E-02", "1.18591415E-02", "1.19979606E+02"});
    model.component("comp1").material("mat5").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "20[degC]");
    model.component("comp1").material("mat5").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat5").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat5").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"-4.14E-6", "1.44E-8", "-6.69E-11", "8.18E-7", "9.54E-10", "0.274"});
    model.component("comp1").material("mat5").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "8282.0E7[Pa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.248");
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance5").func("int1")
         .set("funcname", "taui5");
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance5").func("int1")
         .set("table", new String[][]{{"2400", "0.916"}, 
         {"2200", "0.941"}, 
         {"2000", "0.971"}, 
         {"1800", "0.985"}, 
         {"1600", "0.998"}, 
         {"1400", "0.999"}, 
         {"1200", "0.999"}, 
         {"1060", "0.999"}, 
         {"1000", "0.999"}, 
         {"950", "0.999"}, 
         {"900", "0.999"}, 
         {"850", "0.999"}, 
         {"800", "0.999"}, 
         {"700", "0.998"}, 
         {"650", "0.998"}, 
         {"600", "0.998"}, 
         {"550", "0.998"}, 
         {"500", "0.996"}, 
         {"480", "0.994"}, 
         {"460", "0.993"}, 
         {"440", "0.99"}, 
         {"420", "0.984"}, 
         {"400", "0.961"}, 
         {"390", "0.927"}, 
         {"380", "0.84"}, 
         {"370", "0.61"}, 
         {"360", "0.201"}});
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance5").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance5").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance5").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance5")
         .set("taui5", "taui5(c_const/freq)");
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance5").addInput("frequency");
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2400", "0.839"}, 
         {"2200", "0.885"}, 
         {"2000", "0.942"}, 
         {"1800", "0.969"}, 
         {"1600", "0.996"}, 
         {"1400", "0.998"}, 
         {"1200", "0.998"}, 
         {"1060", "0.998"}, 
         {"1000", "0.998"}, 
         {"950", "0.998"}, 
         {"900", "0.998"}, 
         {"850", "0.998"}, 
         {"800", "0.998"}, 
         {"700", "0.997"}, 
         {"650", "0.996"}, 
         {"600", "0.996"}, 
         {"550", "0.996"}, 
         {"500", "0.991"}, 
         {"480", "0.989"}, 
         {"460", "0.985"}, 
         {"440", "0.98"}, 
         {"420", "0.968"}, 
         {"400", "0.924"}, 
         {"390", "0.859"}, 
         {"380", "0.705"}, 
         {"370", "0.373"}, 
         {"360", "0.04"}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat5").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat6").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott \u70ed\u5149");
    model.component("comp1").material("mat6").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat6").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "\u5185\u90e8\u900f\u5c04\u7387\uff0c10\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat6").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "\u5185\u90e8\u900f\u5c04\u7387\uff0c25\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance25").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat6").label("Schott N-SK11 Glass");
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "3.08[g/cm^3]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"6.5E-6[1/K]", "0", "0", "0", "6.5E-6[1/K]", "0", "0", "0", "6.5E-6[1/K]"});
    model.component("comp1").material("mat6").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.17963631E+00", "2.29817295E-01", "9.35789652E-01", "6.80282081E-03", "2.19737205E-02", "1.01513232E+02"});
    model.component("comp1").material("mat6").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat6").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat6").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat6").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"2.14E-6", "1.27E-8", "-7.21E-11", "3.51E-7", "5.41E-10", "0.238"});
    model.component("comp1").material("mat6").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("E", "79.0[GPa]");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("nu", "0.239");
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2500", "0.782"}, 
         {"2325", "0.882"}, 
         {"1970", "0.967"}, 
         {"1530", "0.994"}, 
         {"1060", "0.998"}, 
         {"700", "0.998"}, 
         {"660", "0.998"}, 
         {"620", "0.998"}, 
         {"580", "0.998"}, 
         {"546", "0.999"}, 
         {"500", "0.998"}, 
         {"460", "0.996"}, 
         {"436", "0.995"}, 
         {"420", "0.994"}, 
         {"405", "0.992"}, 
         {"400", "0.99"}, 
         {"390", "0.988"}, 
         {"380", "0.985"}, 
         {"370", "0.98"}, 
         {"365", "0.976"}, 
         {"350", "0.95"}, 
         {"334", "0.872"}, 
         {"320", "0.7"}, 
         {"310", "0.48"}, 
         {"300", "0.212"}, 
         {"290", "0.058"}});
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance25").func("int1")
         .set("funcname", "taui25");
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance25").func("int1")
         .set("table", new String[][]{{"2500", "0.54"}, 
         {"2325", "0.73"}, 
         {"1970", "0.92"}, 
         {"1530", "0.984"}, 
         {"1060", "0.995"}, 
         {"700", "0.996"}, 
         {"660", "0.995"}, 
         {"620", "0.995"}, 
         {"580", "0.996"}, 
         {"546", "0.997"}, 
         {"500", "0.994"}, 
         {"460", "0.99"}, 
         {"436", "0.988"}, 
         {"420", "0.985"}, 
         {"405", "0.98"}, 
         {"400", "0.975"}, 
         {"390", "0.97"}, 
         {"380", "0.963"}, 
         {"370", "0.95"}, 
         {"365", "0.94"}, 
         {"350", "0.88"}, 
         {"334", "0.71"}, 
         {"320", "0.41"}, 
         {"310", "0.16"}, 
         {"300", "0.02"}});
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance25").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance25").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance25").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance25")
         .set("taui25", "taui25(c_const/freq)");
    model.component("comp1").material("mat6").propertyGroup("InternalTransmittance25").addInput("frequency");
    model.component("comp1").material("mat1").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat3").selection().named("geom1_csel3_dom");
    model.component("comp1").material("mat4").selection().named("geom1_csel4_dom");
    model.component("comp1").material("mat5").selection().named("geom1_pi1_sel2");
    model.component("comp1").material("mat6").selection().named("geom1_pi1_sel3");

    model.component("comp1").physics("gop").prop("WavelengthDistribution")
         .setIndex("WavelengthDistribution", "PolychromaticWavelength", 0);
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("ExteriorUnmeshedProperties")
         .setIndex("DispersionModel", "AirEdlen1953", 0);
    model.component("comp1").physics("gop").prop("ComputeOpticalPathLength")
         .setIndex("ComputeOpticalPathLength", 1, 0);
    model.component("comp1").physics("gop").feature("mp1")
         .set("RefractiveIndexDomains", "GetDispersionModelFromMaterial");
    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").create("xgrat1", "CrossGrating", 2);
    model.component("comp1").physics("gop").feature("xgrat1").selection().named("geom1_wp4_bnd");
    model.component("comp1").physics("gop").feature("xgrat1").set("RaysToRelease", "Reflected");
    model.component("comp1").physics("gop").feature("xgrat1").set("dg1", "sigma_ech");
    model.component("comp1").physics("gop").feature("xgrat1").set("dg2", "sigma_xdp");
    model.component("comp1").physics("gop").feature("xgrat1")
         .set("DirectionOfPeriodicity1", "ParallelToReferenceEdge");
    model.component("comp1").physics("gop").feature("xgrat1").selection("ReferenceEdge1").set(39);
    model.component("comp1").physics("gop").feature("xgrat1")
         .set("DirectionOfPeriodicity2", "ParallelToReferenceEdge");
    model.component("comp1").physics("gop").feature("xgrat1").selection("ReferenceEdge2").set(5);
    model.component("comp1").physics("gop").feature("xgrat1").feature("xdfo0").set("mg", "m");
    model.component("comp1").physics("gop").feature("xgrat1").feature("xdfo0").set("ng", 1);
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_csel7_bnd");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("\u50cf\u5e73\u9762");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_csel9_bnd");
    model.component("comp1").physics("gop").create("rpt1", "ReleaseFromPoint", 0);
    model.component("comp1").physics("gop").feature("rpt1").selection().named("geom1_pt1_pnt");
    model.component("comp1").physics("gop").feature("rpt1").set("RayDirectionVector", "Conical");
    model.component("comp1").physics("gop").feature("rpt1").set("ConicalDistribution", "Hexapolar");
    model.component("comp1").physics("gop").feature("rpt1").setIndex("Nctheta", "N_hex", 0);
    model.component("comp1").physics("gop").feature("rpt1").set("cax", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").feature("rpt1").set("alphac", "atan(NA)");
    model.component("comp1").physics("gop").feature("rpt1").set("LDistributionFunction", "ListOfValues");
    model.component("comp1").physics("gop").feature("rpt1")
         .setIndex("lambda0vals", "range(lam_min,lam_step,lam_max)", 0);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("aveop1").selection().set(76);
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("aveop2").selection().set(77);
    model.component("comp1").cpl().create("aveop3", "Average");
    model.component("comp1").cpl("aveop3").set("axisym", true);
    model.component("comp1").cpl("aveop3").selection().geom("geom1", 0);
    model.component("comp1").cpl("aveop3").selection().set(96);

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("lunit", "mm");
    model.study("std1").feature("rtrac").set("llist", "0 450");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "BFL_doub", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "BFL_doub", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "lam_nom", 0);
    model.study("std1").feature("param").setIndex("plistarr", "lam_mid-50[nm] lam_mid lam_mid+50[nm]", 0);
    model.study("std1").feature("param").setIndex("punit", "nm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol2");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.L");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").label("\u5c04\u7ebf\u56fe");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").set("legendpos", "bottom");
    model.result("pg1").set("legendactive", true);
    model.result("pg1").set("legendprecision", 4);
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").set("data", "ray1");
    model.result("pg1").feature("rtrj1").setIndex("looplevel", 1, 1);
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.lambda0");
    model.result("pg1").feature("rtrj1").feature("col1").set("unit", "nm");
    model.result("pg1").feature("rtrj1").feature("col1").set("colortable", "Spectrum");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("filt1").set("evaluate", "fraction");
    model.result("pg1").feature("rtrj1").feature("filt1").set("fraction", ".05");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("rtrj2", "rtrj1");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj2").setIndex("looplevel", 2, 1);
    model.result("pg1").feature("rtrj2").set("inheritplot", "rtrj1");
    model.result("pg1").feature().duplicate("rtrj3", "rtrj2");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj3").setIndex("looplevel", 3, 1);
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").feature("surf1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().dataset().create("ip1", "IntersectionPoint3D");
    model.result().dataset("ip1").set("genmethod", "threepoint");
    model.result().dataset("ip1").setIndex("genpoints", "aveop1(x)", 0, 0);
    model.result().dataset("ip1").setIndex("genpoints", "aveop1(y)", 0, 1);
    model.result().dataset("ip1").setIndex("genpoints", "aveop1(z)", 0, 2);
    model.result().dataset("ip1").setIndex("genpoints", "aveop2(x)", 1, 0);
    model.result().dataset("ip1").setIndex("genpoints", "aveop2(y)", 1, 1);
    model.result().dataset("ip1").setIndex("genpoints", "aveop2(z)", 1, 2);
    model.result().dataset("ip1").setIndex("genpoints", "aveop3(x)", 2, 0);
    model.result().dataset("ip1").setIndex("genpoints", "aveop3(y)", 2, 1);
    model.result().dataset("ip1").setIndex("genpoints", "aveop3(z)", 2, 2);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u9636\u68af\u5149\u6805\u56fe");
    model.result("pg2").set("data", "none");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").set("legendpos", "bottom");
    model.result("pg2").set("legendactive", true);
    model.result("pg2").set("legendprecision", 4);
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result("pg2").feature("spot1").set("data", "ip1");
    model.result("pg2").feature("spot1").set("additionallogicalexpressionactive", true);
    model.result("pg2").feature("spot1").set("additionallogicalexpression", "comp1.gop.L>100[mm]");
    model.result("pg2").feature("spot1").set("arrangement", "single");
    model.result("pg2").feature("spot1").set("spotsizeactive", false);
    model.result("pg2").feature("spot1").set("sphereradiusscaleactive", true);
    model.result("pg2").feature("spot1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").feature("col1").set("expr", "gop.lambda0");
    model.result("pg2").feature("spot1").feature("col1").set("unit", "nm");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u70b9\u5217\u56fe");
    model.result("pg3").set("data", "none");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").set("legendpos", "bottom");
    model.result("pg3").create("spot1", "SpotDiagram");
    model.result("pg3").feature("spot1").set("data", "ip1");
    model.result("pg3").feature("spot1").set("additionallogicalexpressionactive", true);
    model.result("pg3").feature("spot1").set("additionallogicalexpression", "comp1.gop.L>100[mm]");
    model.result("pg3").feature("spot1").set("arrangement", "wavelength");
    model.result("pg3").feature("spot1").set("layout", "rectangular");
    model.result("pg3").feature("spot1").set("columns", 5);
    model.result("pg3").feature("spot1").set("wavelengthactive", true);
    model.result("pg3").feature("spot1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("spot1").feature("col1").set("expr", "at(0,gop.phic)");
    model.result("pg3").feature("spot1").feature("col1").set("unit", "deg");
    model.result("pg3").run();

    model.view("view14").set("showgrid", false);

    model.title("\u4ea4\u53c9\u5149\u6805\u9636\u68af\u5149\u6805\u5149\u8c31\u4eea");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u201c\u4ea4\u53c9\u5149\u6805\u201d\u7279\u5f81\u5728\u9636\u68af\u5149\u6805\u5149\u8c31\u4eea\u4e2d\u7684\u4f7f\u7528\u3002\u4ea4\u53c9\u5149\u6805\u662f\u5177\u6709\u4e24\u4e2a\u5468\u671f\u6027\u65b9\u5411\u7684\u5149\u6805\u3002\u5728\u6b64\u6a21\u578b\u4e2d\uff0c\u4ea4\u53c9\u5149\u6805\u5728\u4e00\u4e2a\u65b9\u5411\u4e0a\u4ee5\u9ad8\u9636\u65b9\u5f0f\u4f7f\u7528\uff0c\u5728\u6b63\u4ea4\uff08\u201c\u4ea4\u53c9\u201d\uff09\u65b9\u5411\u4e0a\u4ee5\u4e00\u9636\u65b9\u5f0f\u4f7f\u7528\u3002\u901a\u8fc7\u8fd9\u79cd\u65b9\u5f0f\uff0c\u53ef\u4ee5\u7528\u5355\u4e2a\u5149\u6805\u4ea7\u751f\u4e8c\u7ef4\u4ea4\u53c9\u5206\u6563\u5149\u8c31\u3002\u5bf9\u591a\u4e2a\u9636\u6b21\u6267\u884c\u53c2\u6570\u5316\u626b\u63cf\uff0c\u5e76\u751f\u6210\u5c04\u7ebf\u56fe\u548c\u70b9\u5217\u56fe\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("cross_grating_echelle_spectrograph.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
