/*
 * petzval_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:18 by COMSOL 6.3.0.290. */
public class petzval_lens {

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
    model.component("comp1").geom("geom1").insertFile("petzval_lens_geom_sequence.mph", "geom1");
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
    model.component("comp1").physics("gop").create("wall3", "Wall", 2);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("gop").feature("wall3").label("\u50cf");
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
    model.result("pg1").label("\u5c04\u7ebf\u56fe 1");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").set("legendpos", "bottom");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "at('last',gop.rrel)");
    model.result("pg1").feature("rtrj1").feature("col1").set("unit", "\u00b5m");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("filt1").set("type", "logical");
    model.result("pg1").feature("rtrj1").feature("filt1").set("logicalexpr", "at(0,abs(gop.deltaqx))<1[mm]");
    model.result().dataset().create("cpl1", "CutPlane");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "cpl1");
    model.result("pg1").feature("surf1").set("expr", "gop.nrefd");
    model.result("pg1").feature("surf1").set("descr", "\u6298\u5c04\u7387\uff0cd \u7ebf");
    model.result("pg1").feature("surf1").set("colortable", "GrayScale");
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("data", "cpl1");
    model.result("pg1").feature("line1").setIndex("looplevel", 1, 0);
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
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("data", "dset1");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("geom1_csel5_bnd");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u70b9\u5217\u56fe");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("spot1", "SpotDiagram");
    model.result("pg3").feature("spot1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("spot1").feature("col1").set("expr", "gop.lambda0");
    model.result("pg3").feature("spot1").feature("col1").set("unit", "nm");
    model.result("pg3").feature("spot1").feature("col1").set("rangecoloractive", true);
    model.result("pg3").feature("spot1").feature("col1").set("rangecolormin", 450);
    model.result("pg3").feature("spot1").feature("col1").set("rangecolormax", 650);
    model.result("pg3").run();

    model.title("Petzval \u900f\u955c");

    model
         .description("\u672c\u6559\u7a0b\u4ecb\u7ecd\u5982\u4f55\u8bbe\u7f6e\u591a\u5143\u7269\u955c\u3002\u6240\u9009\u900f\u955c\u4e3a\u5e26\u89c6\u573a\u81f4\u5e73\u5668\u7684 Petzval \u900f\u955c\uff0cM. Kidger \u6240\u8457\u7684 \"Fundamental Optical Design\" 2001 \u7248\u7b2c 192\u00a0\u9875\u63cf\u8ff0\u4e86\u8fd9\u79cd\u900f\u955c\u3002\u6f14\u793a\u4e86\u5982\u4f55\u4f7f\u7528 COMSOL \u96f6\u4ef6\u5e93\u4e2d\u7684\u201c\u7403\u9762\u900f\u955c\uff08\u4e09\u7ef4\uff09\u201d\u96f6\u4ef6\u6765\u5305\u542b\u51e0\u4f55\u5e8f\u5217\uff0c\u4ee5\u53ca\u5982\u4f55\u4f7f\u7528\u901a\u5149\u5b54\u5f84\u4ee5\u53ca\u8fb9\u7f18\u548c\u5b54\u5f84\u969c\u788d\u7269\u3002\u6700\u540e\uff0c\u4ee5\u56fe\u5f62\u65b9\u5f0f\u663e\u793a\u4e86\u591a\u4e2a\u6ce2\u957f\u548c\u89c6\u573a\u89d2\u4e0b\u57fa\u4e8e\u6805\u683c\u7684\u5149\u7ebf\u8ffd\u8ff9\u7ed3\u679c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("petzval_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
