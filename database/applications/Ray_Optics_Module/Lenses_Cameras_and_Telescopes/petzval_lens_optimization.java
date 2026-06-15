/*
 * petzval_lens_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:18 by COMSOL 6.3.0.290. */
public class petzval_lens_optimization {

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
    model.param("par2").set("theta_x1", "0.0[deg]", "\u89c6\u573a\u89d2 1\uff0cx \u5206\u91cf");
    model.param("par2").set("theta_y1", "0.0[deg]", "\u89c6\u573a\u89d2 1\uff0cy \u5206\u91cf");
    model.param("par2").set("theta_x2", "0.0[deg]", "\u89c6\u573a\u89d2 2\uff0cx \u5206\u91cf");
    model.param("par2").set("theta_y2", "6.0[deg]", "\u89c6\u573a\u89d2 2\uff0cy \u5206\u91cf");
    model.param("par2").set("theta_x3", "0.0[deg]", "\u89c6\u573a\u89d2 3\uff0cx \u5206\u91cf");
    model.param("par2").set("theta_y3", "9.0[deg]", "\u89c6\u573a\u89d2 3\uff0cy \u5206\u91cf");
    model.param("par2").set("N_ring", "12", "\u516d\u8fb9\u73af\u6570");
    model.param("par2").set("P_nom", "41.5[mm]", "\u6807\u79f0\u5165\u5c04\u5149\u77b3\u76f4\u5f84");
    model.param("par2").set("vx1", "tan(theta_x1)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf 1\uff0cx \u5206\u91cf");
    model.param("par2").set("vy1", "tan(theta_y1)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf 1\uff0cy \u5206\u91cf");
    model.param("par2").set("vx2", "tan(theta_x2)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf 2\uff0cx \u5206\u91cf");
    model.param("par2").set("vy2", "tan(theta_y2)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf 2\uff0cy \u5206\u91cf");
    model.param("par2").set("vx3", "tan(theta_x3)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf 3\uff0cx \u5206\u91cf");
    model.param("par2").set("vy3", "tan(theta_y3)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf 3\uff0cy \u5206\u91cf");
    model.param("par2").set("vz", "1", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.param("par2").set("z_stop", "Tc_1+T_1+Tc_2+T_2", "\u5149\u9611 z \u5750\u6807");
    model.param("par2")
         .set("z_image", "Tc_1+T_1+Tc_2+T_2+Tc_3+T_3+Tc_4+T_4+Tc_5+T_5+Tc_6+T_6", "\u50cf\u5e73\u9762\u540d\u4e49 z \u5750\u6807");
    model.param("par2").set("P1_fac", "-1.142", "\u5149\u77b3\u504f\u79fb\u5e38\u6570 1");
    model.param("par2").set("P2_fac", "-0.080", "\u5149\u77b3\u504f\u79fb\u5e38\u6570 2");
    model.param("par2")
         .set("P_fac1", "P1_fac+P2_fac*sin(sqrt(theta_x1^2+theta_y1^2))", "\u5149\u77b3\u504f\u79fb\u56e0\u5b50\uff0c\u89c6\u573a 1");
    model.param("par2")
         .set("P_fac2", "P1_fac+P2_fac*sin(sqrt(theta_x2^2+theta_y2^2))", "\u5149\u77b3\u504f\u79fb\u56e0\u5b50\uff0c\u89c6\u573a 2");
    model.param("par2")
         .set("P_fac3", "P1_fac+P2_fac*sin(sqrt(theta_x3^2+theta_y3^2))", "\u5149\u77b3\u504f\u79fb\u56e0\u5b50\uff0c\u89c6\u573a 3");
    model.param("par2")
         .set("dx1", "(dz+P_fac1*z_stop)*tan(theta_x1)", "\u5149\u77b3\u4e2d\u5fc3\uff0c\u89c6\u573a 1\uff0cx \u5750\u6807");
    model.param("par2")
         .set("dy1", "(dz+P_fac1*z_stop)*tan(theta_y1)", "\u5149\u77b3\u4e2d\u5fc3\uff0c\u89c6\u573a 1\uff0cy \u5750\u6807");
    model.param("par2")
         .set("dx2", "(dz+P_fac2*z_stop)*tan(theta_x2)", "\u5149\u77b3\u4e2d\u5fc3\uff0c\u89c6\u573a 2\uff0cx \u5750\u6807");
    model.param("par2")
         .set("dy2", "(dz+P_fac2*z_stop)*tan(theta_y2)", "\u5149\u77b3\u4e2d\u5fc3\uff0c\u89c6\u573a 2\uff0cy \u5750\u6807");
    model.param("par2")
         .set("dx3", "(dz+P_fac3*z_stop)*tan(theta_x3)", "\u5149\u77b3\u4e2d\u5fc3\uff0c\u89c6\u573a 3\uff0cx \u5750\u6807");
    model.param("par2")
         .set("dy3", "(dz+P_fac3*z_stop)*tan(theta_y3)", "\u5149\u77b3\u4e2d\u5fc3\uff0c\u89c6\u573a 3\uff0cy \u5750\u6807");
    model.param("par2").set("dz", "-5[mm]", "\u5149\u77b3\u4e2d\u5fc3\uff0cz \u5206\u91cf");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").label("Petzval \u900f\u955c");
    model.component("comp1").geom("geom1").insertFile("petzval_lens_optimization_geom_sequence.mph", "geom1");
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
    model.component("comp1").material("mat1").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat3").selection().named("geom1_csel3_dom");
    model.component("comp1").material("mat4").selection().named("geom1_csel4_dom");

    model.component("comp1").physics("gop").prop("WavelengthDistribution")
         .setIndex("WavelengthDistribution", "PolychromaticWavelength", 0);
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("ExteriorUnmeshedProperties")
         .setIndex("DispersionModel", "AirEdlen1953", 0);
    model.component("comp1").physics("gop").feature("mp1")
         .set("RefractiveIndexDomains", "GetDispersionModelFromMaterial");
    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("GridType", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").set("qcc", new String[]{"dx1", "dy1", "dz"});
    model.component("comp1").physics("gop").feature("relg1").set("rcc", new String[]{"nix", "niy", "niz"});
    model.component("comp1").physics("gop").feature("relg1").set("Rc", "P_nom/2");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Ncr", "N_ring", 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new String[]{"vx1", "vy1", "vz"});
    model.component("comp1").physics("gop").feature("relg1").set("LDistributionFunction", "ListOfValues");
    model.component("comp1").physics("gop").feature("relg1").setIndex("lambda0vals", "475[nm] 550[nm] 625[nm]", 0);
    model.component("comp1").physics("gop").feature().duplicate("relg2", "relg1");
    model.component("comp1").physics("gop").feature("relg2").set("qcc", new String[]{"dx2", "dy2", "dz"});
    model.component("comp1").physics("gop").feature("relg2").set("L0", new String[]{"vx2", "vy2", "vz"});
    model.component("comp1").physics("gop").feature().duplicate("relg3", "relg2");
    model.component("comp1").physics("gop").feature("relg3").set("qcc", new String[]{"dx3", "dy3", "dz"});
    model.component("comp1").physics("gop").feature("relg3").set("L0", new String[]{"vx3", "vy3", "vz"});
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_csel7_bnd");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("\u5149\u9611");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_csel8_bnd");
    model.component("comp1").physics("gop").feature("wall2").set("WallCondition", "Disappear");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("gop").create("wall3", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall3").label("\u50cf\u9762");
    model.component("comp1").physics("gop").feature("wall3").selection().named("geom1_csel9_bnd");

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
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").set("showlegends", false);

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u70b9\u5217\u56fe");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result("pg2").feature("spot1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").feature("col1").set("expr", "gop.lambda0");
    model.result("pg2").feature("spot1").feature("col1").set("unit", "nm");
    model.result("pg2").feature("spot1").feature("col1").set("rangecoloractive", true);
    model.result("pg2").feature("spot1").feature("col1").set("rangecolormin", 450);
    model.result("pg2").feature("spot1").feature("col1").set("rangecolormax", 650);
    model.result("pg2").run();

    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570 3\uff1a\u4f18\u5316");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("dR1_1", "0[mm]", "L1\uff0c\u8868\u9762 1 \u8c03\u6574");
    model.param("par3").set("dR2_1", "0[mm]", "L1\uff0c\u8868\u9762 2 \u8c03\u6574");
    model.param("par3").set("dR2_2", "0[mm]", "L2\uff0c\u8868\u9762 2 \u8c03\u6574");
    model.param("par3").set("dR1_4", "0[mm]", "L3\uff0c\u8868\u9762 1 \u8c03\u6574");
    model.param("par3").set("dR2_4", "0[mm]", "L3\uff0c\u8868\u9762 2 \u8c03\u6574");
    model.param("par3").set("dR2_5", "0[mm]", "L4\uff0c\u8868\u9762 2 \u8c03\u6574");
    model.param("par3").set("dR1_6", "0[mm]", "L5\uff0c\u8868\u9762 1 \u8c03\u6574");
    model.param().set("R1_1", "99.56266[mm]+dR1_1");
    model.param().descr("R1_1", "L1\uff0c\u9762 1 \u66f2\u7387\u534a\u5f84");
    model.param().set("R2_1", "-86.84002[mm]+dR2_1");
    model.param().descr("R2_1", "L1\uff0c\u9762 2 \u66f2\u7387\u534a\u5f84");
    model.param().set("R2_2", "-1187.63858[mm]+dR2_2");
    model.param().descr("R2_2", "L2\uff0c\u9762 2 \u66f2\u7387\u534a\u5f84");
    model.param().set("R1_4", "57.47191[mm]+dR1_4");
    model.param().descr("R1_4", "L3\uff0c\u9762 1 \u66f2\u7387\u534a\u5f84");
    model.param().set("R2_4", "-54.61865[mm]+dR2_4");
    model.param().descr("R2_4", "L3\uff0c\u9762 2 \u66f2\u7387\u534a\u5f84");
    model.param().set("R2_5", "-614.68633[mm]+dR2_5");
    model.param().descr("R2_5", "L4\uff0c\u9762 2 \u66f2\u7387\u534a\u5f84");
    model.param().set("R1_6", "-38.17110[mm]+dR1_6");
    model.param().descr("R1_6", "L5\uff0c\u9762 1 \u66f2\u7387\u534a\u5f84");

    model.study().create("std2");
    model.study("std2").create("rtrac", "RayTracing");
    model.study("std2").feature("rtrac").setSolveFor("/physics/gop", true);
    model.study("std2").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std2").feature("rtrac").set("llist", "0 200");
    model.study("std2").feature("rtrac").set("lunit", "mm");
    model.study("std2").create("opt", "Optimization");
    model.study("std2").feature("opt").set("optsolver", "bobyqa");
    model.study("std2").feature("opt").set("opttol", 0.02);
    model.study("std2").feature("opt").setIndex("optobj", "comp1.gop.relg1.rrms^2", 0);
    model.study("std2").feature("opt").setIndex("optobj", "comp1.gop.relg2.rrms^2", 1);
    model.study("std2").feature("opt").setIndex("optobj", "comp1.gop.relg3.rrms^2", 2);
    model.study("std2").feature("opt")
         .set("pname", new String[]{"dR1_1", "dR2_1", "dR2_2", "dR1_4", "dR2_4", "dR2_5", "dR1_6"});
    model.study("std2").feature("opt")
         .set("initval", new String[]{"0[mm]", "0[mm]", "0[mm]", "0[mm]", "0[mm]", "0[mm]", "0[mm]"});
    model.study("std2").feature("opt").set("scale", new String[]{"0.01", "0.01", "1", "0.01", "0.01", "1", "0.01"});
    model.study("std2").feature("opt")
         .set("lbound", new String[]{"-20[mm]", "-20[mm]", "-500[mm]", "-10[mm]", "-10[mm]", "-500[mm]", "-10[mm]"});
    model.study("std2").feature("opt")
         .set("ubound", new String[]{"20[mm]", "20[mm]", "500[mm]", "10[mm]", "10[mm]", "500[mm]", "10[mm]"});
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol2").feature("t1").set("timestepgenalpha", "200[mm]/c_const");

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("o1").run("compute");

    model.result().dataset().create("ray2", "Ray");
    model.result().dataset("ray2").set("solution", "sol3");
    model.result().dataset("ray2").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray2").set("geom", "geom1");
    model.result().dataset("ray2").set("rgeom", "pgeom_gop");
    model.result().dataset("ray2").set("rgeomspec", "fromphysics");
    model.result().dataset("ray2").set("physicsinterface", "gop");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "ray2");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").setIndex("looplevel", 1, 1);
    model.result("pg3").label("\u5c04\u7ebf\u8f68\u8ff9 (gop) 1");
    model.result("pg3").create("rtrj1", "RayTrajectories");
    model.result("pg3").feature("rtrj1").set("linetype", "line");
    model.result("pg3").feature("rtrj1").create("col1", "Color");
    model.result("pg3").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg3").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg3").run();

    model.study("std2").feature("opt").set("probewindow", "");

    model.result("pg3").set("showlegends", false);
    model.result("pg3").run();
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").set("data", "ray2");
    model.result("pg4").run();

    model.title("Petzval \u900f\u955c\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5f53\u591a\u5143\u7269\u955c\u4e2d\u7684\u4e00\u5757\u5149\u5b66\u73bb\u7483\u88ab\u66f4\u6362\u65f6\uff0c\u5982\u4f55\u4f7f\u7528\u201c\u4f18\u5316\u201d\u7814\u7a76\u6765\u66f4\u65b0\u8be5\u7269\u955c\u7684\u5149\u5b66\u65b9\u6848\u3002\u4ece\u539f\u59cb\u65b9\u6848\u5f00\u59cb\uff0c\u4f7f\u7528\u5177\u6709\u51e0\u4e4e\u76f8\u540c d \u7ebf\u6298\u5c04\u7387\u548c\u963f\u8d1d\u6570\u7684\u5149\u5b66\u73bb\u7483\u66ff\u6362\u5176\u4e2d\u4e00\u4e2a\u5143\u4ef6\u7684\u5149\u5b66\u73bb\u7483\u3002\u5728\u201c\u4f18\u5316\u201d\u7814\u7a76\u4e2d\uff0c\u9009\u62e9\u900f\u955c\u8868\u9762\u66f2\u7387\u534a\u5f84\u7684\u6270\u52a8\u4f5c\u4e3a\u63a7\u5236\u53c2\u6570\uff0c\u5e76\u9009\u62e9\u4e09\u4e2a\u4e0d\u540c\u89c6\u573a\u89d2\u7684\u5149\u6591 RMS \u5c3a\u5bf8\u4f5c\u4e3a\u76ee\u6807\u51fd\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("petzval_lens_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
