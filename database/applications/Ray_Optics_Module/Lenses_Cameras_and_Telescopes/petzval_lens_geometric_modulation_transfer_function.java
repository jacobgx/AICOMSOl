/*
 * petzval_lens_geometric_modulation_transfer_function.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:18 by COMSOL 6.3.0.290. */
public class petzval_lens_geometric_modulation_transfer_function {

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

    model.param().label("Parameters 1: Lens Prescription");
    model.param().create("par2");
    model.param("par2").label("Parameters 2: General");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("theta_x1", "0.0[deg]", "Field angle 1, x-component");
    model.param("par2").set("theta_y1", "0.0[deg]", "Field angle 1, y-component");
    model.param("par2").set("theta_x2", "0.0[deg]", "Field angle 2, x-component");
    model.param("par2").set("theta_y2", "6.0[deg]", "Field angle 2, y-component");
    model.param("par2").set("theta_x3", "0.0[deg]", "Field angle 3, x-component");
    model.param("par2").set("theta_y3", "9.0[deg]", "Field angle 3, y-component");
    model.param("par2").set("N_ring", "12", "Number of hexapolar rings");
    model.param("par2").set("P_nom", "41.5[mm]", "Nominal entrance pupil diameter");
    model.param("par2").set("vx1", "tan(theta_x1)", "Ray direction vector 1, x-component");
    model.param("par2").set("vy1", "tan(theta_y1)", "Ray direction vector 1, y-component");
    model.param("par2").set("vx2", "tan(theta_x2)", "Ray direction vector 2, x-component");
    model.param("par2").set("vy2", "tan(theta_y2)", "Ray direction vector 2, y-component");
    model.param("par2").set("vx3", "tan(theta_x3)", "Ray direction vector 3, x-component");
    model.param("par2").set("vy3", "tan(theta_y3)", "Ray direction vector 3, y-component");
    model.param("par2").set("vz", "1", "Ray direction vector, z-component");
    model.param("par2").set("z_stop", "Tc_1+T_1+Tc_2+T_2", "Stop z-coordinate");
    model.param("par2")
         .set("z_image", "Tc_1+T_1+Tc_2+T_2+Tc_3+T_3+Tc_4+T_4+Tc_5+T_5+Tc_6+T_6", "Image plane nominal z-coordinate");
    model.param("par2").set("P1_fac", "-1.142", "Pupil shift constant 1");
    model.param("par2").set("P2_fac", "-0.080", "Pupil shift constant 2");
    model.param("par2")
         .set("P_fac1", "P1_fac+P2_fac*sin(sqrt(theta_x1^2+theta_y1^2))", "Pupil shift factor, field 1");
    model.param("par2")
         .set("P_fac2", "P1_fac+P2_fac*sin(sqrt(theta_x2^2+theta_y2^2))", "Pupil shift factor, field 2");
    model.param("par2")
         .set("P_fac3", "P1_fac+P2_fac*sin(sqrt(theta_x3^2+theta_y3^2))", "Pupil shift factor, field 3");
    model.param("par2").set("dx1", "(dz+P_fac1*z_stop)*tan(theta_x1)", "Pupil center, field 1, x-coordinate");
    model.param("par2").set("dy1", "(dz+P_fac1*z_stop)*tan(theta_y1)", "Pupil center, field 1, y-coordinate");
    model.param("par2").set("dx2", "(dz+P_fac2*z_stop)*tan(theta_x2)", "Pupil center, field 2, x-coordinate");
    model.param("par2").set("dy2", "(dz+P_fac2*z_stop)*tan(theta_y2)", "Pupil center, field 2, y-coordinate");
    model.param("par2").set("dx3", "(dz+P_fac3*z_stop)*tan(theta_x3)", "Pupil center, field 3, x-coordinate");
    model.param("par2").set("dy3", "(dz+P_fac3*z_stop)*tan(theta_y3)", "Pupil center, field 3, y-coordinate");
    model.param("par2").set("dz", "-5[mm]", "Pupil center, z-component");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").label("Petzval Lens");
    model.component("comp1").geom("geom1").insertFile("petzval_lens_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott thermo-optic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "Internal transmittance, 10\u00a0mm sample thickness");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "Internal transmittance, 25\u00a0mm sample thickness");
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
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott thermo-optic");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "Internal transmittance, 10\u00a0mm sample thickness");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "Internal transmittance, 25\u00a0mm sample thickness");
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
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott thermo-optic");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "Internal transmittance, 10\u00a0mm sample thickness");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "Internal transmittance, 25\u00a0mm sample thickness");
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
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott thermo-optic");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "Internal transmittance, 10\u00a0mm sample thickness");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "Internal transmittance, 25\u00a0mm sample thickness");
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
    model.component("comp1").physics("gop").feature("wall1").label("Obstructions");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_csel7_bnd");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("Stop");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_csel8_bnd");
    model.component("comp1").physics("gop").feature("wall2").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall3", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall3").label("Image");
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

    return model;
  }

  public static Model run2(Model model) {
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("Ray Trajectories (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").label("Ray Diagram 1");
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
    model.result("pg1").feature("surf1").set("descr", "Refractive index, d-line");
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
    model.result("pg2").label("Ray Diagram 2");
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
    model.result("pg3").label("Spot Diagram");
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

    model.label("petzval_lens.mph");

    model.result("pg3").run();

    model.component("comp1").physics("gop").prop("WavelengthDistribution")
         .setIndex("WavelengthDistribution", "Monochromatic", 0);
    model.component("comp1").physics("gop").prop("Results").setIndex("Results", "PlotSpotDiagramAndGeometricMTF", 0);
    model.component("comp1").physics("gop").feature("op1").set("lambda0", "550[nm]");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg2").set("boxlengths", new double[]{72.5, 72.5, 158.56813378784963});
    model.result("pg2").feature("rtrj1").set("sphereradiusscale", 1);
    model.result("pg2").feature("rtrj1").set("sphereradiusscaleactive", false);
    model.result("pg2").feature("rtrj1").set("tailscale", 1);
    model.result("pg2").feature("rtrj1").set("tailscaleactive", false);
    model.result("pg2").feature("rtrj1").set("arrowscale", 0);
    model.result("pg2").feature("rtrj1").set("arrowscaleactive", false);
    model.result("pg2").feature("rtrj1").set("ellipsearrowscale", 1);
    model.result("pg2").feature("rtrj1").set("ellipsearrowscaleactive", false);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangeunit", "\u00b5m");
    model.result("pg2").feature("rtrj1").feature("col1").set("rangecolormin", 5.9204146304504615E-5);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangecolormax", 8.530762920971735);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangecoloractive", "off");
    model.result("pg2").feature("rtrj1").feature("col1")
         .set("rangeactualminmax", new double[]{5.9204146304504615E-5, 8.530762920971735});
    model.result("pg2").feature("rtrj1").feature("col1").set("rangeisshared", false);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangeminpositive", 5.9204146304504615E-5);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangedatamin", 5.9204146304504615E-5);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangedatamax", 8.530762920971735);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangedataactive", "off");
    model.result("pg2").feature("rtrj1").set("renderinfo", "1 Edge3D 1 1 4 1 Color");
    model.result("pg2").feature("rtrj1").set("boxlengths", new double[]{41.5, 53.10047912597656, 167.7769012451172});
    model.result("pg2").feature("surf1").set("rangeunit", "1");
    model.result("pg2").feature("surf1").set("rangecolormin", 1);
    model.result("pg2").feature("surf1").set("rangecolormax", 1);
    model.result("pg2").feature("surf1").set("rangecoloractive", "off");
    model.result("pg2").feature("surf1").set("rangeactualminmax", new double[]{1, 1});
    model.result("pg2").feature("surf1").set("rangeisshared", false);
    model.result("pg2").feature("surf1").set("rangeminpositive", 1);
    model.result("pg2").feature("surf1").set("rangedatamin", 1);
    model.result("pg2").feature("surf1").set("rangedatamax", 1);
    model.result("pg2").feature("surf1").set("rangedataactive", "off");
    model.result("pg2").feature("surf1").set("renderinfo", "1 Surface3D 1 1 0 1 Color");
    model.result("pg2").feature("surf1").set("boxlengths", new double[]{58, 58, 160.8213348388672});
    model.result("pg3").feature("spot1").set("truesphereradiusscale", 0.6071767407189164);
    model.result("pg3").feature("spot1")
         .set("labels", new String[]{"$r_{\\textrm{rms}} = \\textrm{3.90 \\textmu m}$", "$r_{\\textrm{rms}} = \\textrm{4.63 \\textmu m}$", "$r_{\\textrm{rms}} = \\textrm{3.48 \\textmu m}$"});
    model.result("pg3").feature("spot1").set("sphereradiusscale", 0.6071767407189164);
    model.result("pg3").feature("spot1").set("sphereradiusscaleactive", false);
    model.result("pg3").feature("spot1").feature("col1").set("rangeunit", "nm");
    model.result("pg3").feature("spot1").feature("col1").set("rangeactualminmax", new double[]{550, 550});
    model.result("pg3").feature("spot1").feature("col1").set("rangeisshared", false);
    model.result("pg3").feature("spot1").feature("col1").set("rangeminpositive", 550);
    model.result("pg3").feature("spot1").feature("col1").set("rangedatamin", 550);
    model.result("pg3").feature("spot1").feature("col1").set("rangedatamax", 550);
    model.result("pg3").feature("spot1").feature("col1").set("rangedataactive", "off");
    model.result("pg3").feature("spot1")
         .set("renderinfo", "6 Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D 6 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color");
    model.result("pg3").feature("spot1").set("boxlengths", new double[]{0.03612932190299034, 0.039093323051929474});
    model.result("pg3").set("xlabel", "");
    model.result("pg3").set("ylabel", "");
    model.result("pg1").run();

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.result().dataset().create("ray2", "Ray");
    model.result().dataset("ray2").set("solution", "sol1");
    model.result().dataset("ray2").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray2").set("geom", "geom1");
    model.result().dataset("ray2").set("rgeom", "pgeom_gop");
    model.result().dataset("ray2").set("rgeomspec", "fromphysics");
    model.result().dataset("ray2").set("physicsinterface", "gop");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "ray2");
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg4").create("rtrj1", "RayTrajectories");
    model.result("pg4").feature("rtrj1").set("linetype", "line");
    model.result("pg4").feature("rtrj1").create("col1", "Color");
    model.result("pg4").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg4").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("Spot Diagram 1");
    model.result("pg5").create("spot1", "SpotDiagram");
    model.result().dataset().create("ip1", "IntersectionPoint3D");
    model.result().dataset("ip1").set("data", "ray2");
    model.result("pg5").feature("spot1").set("data", "ip1");
    model.result("pg5").run();
    model.result("pg5").feature("spot1").runCommand("recomputeFocalPlaneDataset");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").label("Geometric MTF");
    model.result("pg6").set("data", "none");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "Frequency (1/mm)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "MTF");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "Geometric Modulation Transfer Function (MTF)");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("LSF Data (relg1)");
    model.result().evaluationGroup("eg1").set("data", "ip1");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").set("concatenation", "vertical");
    model.result().evaluationGroup("eg1").set("tablebuffersize", 100000);
    model.result().evaluationGroup("eg1").create("ray1", "Ray");
    model.result().evaluationGroup("eg1").feature("ray1").set("data", "ip1");
    model.result().evaluationGroup("eg1").feature("ray1").set("expr", "if(gop.prf==1, ip1y, NaN)");
    model.result().evaluationGroup("eg1").feature("ray1").set("unit", "mm");
    model.result().evaluationGroup("eg1").create("ray2", "Ray");
    model.result().evaluationGroup("eg1").feature("ray2").set("data", "ip1");
    model.result().evaluationGroup("eg1").feature("ray2").set("expr", "if(gop.prf==1, ip1x, NaN)");
    model.result().evaluationGroup("eg1").feature("ray2").set("unit", "mm");
    model.result().evaluationGroup("eg1").run();
    model.result().dataset().create("kde1", "KernelDensityEstimation");
    model.result().dataset("kde1").label("LSFx (eg1)");
    model.result().dataset("kde1").set("kerneltype", "parabolic");
    model.result().dataset("kde1").set("source", "evaluationgroup");
    model.result().dataset("kde1").set("evaluationgroup", "eg1");
    model.result().dataset("kde1").set("xaxisdata", "1");
    model.result().dataset("kde1").set("xvar", "eg1_x");
    model.result().dataset("kde1").set("outvar", "eg1_pdf_x");
    model.result().dataset().create("kde2", "KernelDensityEstimation");
    model.result().dataset("kde2").label("LSFy (eg1)");
    model.result().dataset("kde2").set("kerneltype", "parabolic");
    model.result().dataset("kde2").set("source", "evaluationgroup");
    model.result().dataset("kde2").set("evaluationgroup", "eg1");
    model.result().dataset("kde2").set("xaxisdata", "2");
    model.result().dataset("kde2").set("xvar", "eg1_x");
    model.result().dataset("kde2").set("outvar", "eg1_pdf_y");
    model.result().dataset().create("sfft1", "SpatialFFT");
    model.result().dataset("sfft1").label("MTFx (eg1)");
    model.result().dataset("sfft1").set("data", "kde1");
    model.result().dataset("sfft1").set("gridres", "manual");
    model.result().dataset("sfft1").set("sampresx", 32);
    model.result().dataset("sfft1").set("layout", "padding");
    model.result().dataset("sfft1").set("padx", 992);
    model.result().dataset("sfft1").set("maskdc", false);
    model.result().dataset("sfft1").set("normalizebydc", true);
    model.result().dataset("sfft1").set("fxvar", "eg1_fx");
    model.result().dataset().create("sfft2", "SpatialFFT");
    model.result().dataset("sfft2").label("MTFy (eg1)");
    model.result().dataset("sfft2").set("data", "kde2");
    model.result().dataset("sfft2").set("gridres", "manual");
    model.result().dataset("sfft2").set("sampresx", 32);
    model.result().dataset("sfft2").set("layout", "padding");
    model.result().dataset("sfft2").set("padx", 992);
    model.result().dataset("sfft2").set("maskdc", false);
    model.result().dataset("sfft2").set("normalizebydc", true);
    model.result().dataset("sfft2").set("fxvar", "eg1_fx");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("data", "sfft1");
    model.result("pg6").feature("lngr1").set("expr", "abs(fft(eg1_pdf_x))");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "eg1_fx");
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "MTFx (relg1)", 0);
    model.result("pg6").feature("lngr1").label("MTFx (relg1)");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr2").set("linewidth", "preference");
    model.result("pg6").feature("lngr2").set("data", "sfft2");
    model.result("pg6").feature("lngr2").set("expr", "abs(fft(eg1_pdf_y))");
    model.result("pg6").feature("lngr2").set("xdata", "expr");
    model.result("pg6").feature("lngr2").set("xdataexpr", "eg1_fx");
    model.result("pg6").feature("lngr2").set("legendmethod", "manual");
    model.result("pg6").feature("lngr2").setIndex("legends", "MTFy (relg1)", 0);
    model.result("pg6").feature("lngr2").label("MTFy (relg1)");
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("LSF Data (relg2)");
    model.result().evaluationGroup("eg2").set("data", "ip1");
    model.result().evaluationGroup("eg2").set("transpose", true);
    model.result().evaluationGroup("eg2").set("concatenation", "vertical");
    model.result().evaluationGroup("eg2").set("tablebuffersize", 100000);
    model.result().evaluationGroup("eg2").create("ray1", "Ray");
    model.result().evaluationGroup("eg2").feature("ray1").set("data", "ip1");
    model.result().evaluationGroup("eg2").feature("ray1").set("expr", "if(gop.prf==2, ip1y, NaN)");
    model.result().evaluationGroup("eg2").feature("ray1").set("unit", "mm");
    model.result().evaluationGroup("eg2").create("ray2", "Ray");
    model.result().evaluationGroup("eg2").feature("ray2").set("data", "ip1");
    model.result().evaluationGroup("eg2").feature("ray2").set("expr", "if(gop.prf==2, ip1x, NaN)");
    model.result().evaluationGroup("eg2").feature("ray2").set("unit", "mm");
    model.result().evaluationGroup("eg2").run();
    model.result().dataset().create("kde3", "KernelDensityEstimation");
    model.result().dataset("kde3").label("LSFx (eg2)");
    model.result().dataset("kde3").set("kerneltype", "parabolic");
    model.result().dataset("kde3").set("source", "evaluationgroup");
    model.result().dataset("kde3").set("evaluationgroup", "eg2");
    model.result().dataset("kde3").set("xaxisdata", "1");
    model.result().dataset("kde3").set("xvar", "eg2_x");
    model.result().dataset("kde3").set("outvar", "eg2_pdf_x");
    model.result().dataset().create("kde4", "KernelDensityEstimation");
    model.result().dataset("kde4").label("LSFy (eg2)");
    model.result().dataset("kde4").set("kerneltype", "parabolic");
    model.result().dataset("kde4").set("source", "evaluationgroup");
    model.result().dataset("kde4").set("evaluationgroup", "eg2");
    model.result().dataset("kde4").set("xaxisdata", "2");
    model.result().dataset("kde4").set("xvar", "eg2_x");
    model.result().dataset("kde4").set("outvar", "eg2_pdf_y");
    model.result().dataset().create("sfft3", "SpatialFFT");
    model.result().dataset("sfft3").label("MTFx (eg2)");
    model.result().dataset("sfft3").set("data", "kde3");
    model.result().dataset("sfft3").set("gridres", "manual");
    model.result().dataset("sfft3").set("sampresx", 32);
    model.result().dataset("sfft3").set("layout", "padding");
    model.result().dataset("sfft3").set("padx", 992);
    model.result().dataset("sfft3").set("maskdc", false);
    model.result().dataset("sfft3").set("normalizebydc", true);
    model.result().dataset("sfft3").set("fxvar", "eg2_fx");
    model.result().dataset().create("sfft4", "SpatialFFT");
    model.result().dataset("sfft4").label("MTFy (eg2)");
    model.result().dataset("sfft4").set("data", "kde4");
    model.result().dataset("sfft4").set("gridres", "manual");
    model.result().dataset("sfft4").set("sampresx", 32);
    model.result().dataset("sfft4").set("layout", "padding");
    model.result().dataset("sfft4").set("padx", 992);
    model.result().dataset("sfft4").set("maskdc", false);
    model.result().dataset("sfft4").set("normalizebydc", true);
    model.result().dataset("sfft4").set("fxvar", "eg2_fx");
    model.result("pg6").create("lngr3", "LineGraph");
    model.result("pg6").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr3").set("linewidth", "preference");
    model.result("pg6").feature("lngr3").set("data", "sfft3");
    model.result("pg6").feature("lngr3").set("expr", "abs(fft(eg2_pdf_x))");
    model.result("pg6").feature("lngr3").set("xdata", "expr");
    model.result("pg6").feature("lngr3").set("xdataexpr", "eg2_fx");
    model.result("pg6").feature("lngr3").set("legendmethod", "manual");
    model.result("pg6").feature("lngr3").setIndex("legends", "MTFx (relg2)", 0);
    model.result("pg6").feature("lngr3").label("MTFx (relg2)");
    model.result("pg6").feature("lngr3").set("legend", true);
    model.result("pg6").create("lngr4", "LineGraph");
    model.result("pg6").feature("lngr4").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr4").set("linewidth", "preference");
    model.result("pg6").feature("lngr4").set("data", "sfft4");
    model.result("pg6").feature("lngr4").set("expr", "abs(fft(eg2_pdf_y))");
    model.result("pg6").feature("lngr4").set("xdata", "expr");
    model.result("pg6").feature("lngr4").set("xdataexpr", "eg2_fx");
    model.result("pg6").feature("lngr4").set("legendmethod", "manual");
    model.result("pg6").feature("lngr4").setIndex("legends", "MTFy (relg2)", 0);
    model.result("pg6").feature("lngr4").label("MTFy (relg2)");
    model.result("pg6").feature("lngr4").set("legend", true);
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").label("LSF Data (relg3)");
    model.result().evaluationGroup("eg3").set("data", "ip1");
    model.result().evaluationGroup("eg3").set("transpose", true);
    model.result().evaluationGroup("eg3").set("concatenation", "vertical");
    model.result().evaluationGroup("eg3").set("tablebuffersize", 100000);
    model.result().evaluationGroup("eg3").create("ray1", "Ray");
    model.result().evaluationGroup("eg3").feature("ray1").set("data", "ip1");
    model.result().evaluationGroup("eg3").feature("ray1").set("expr", "if(gop.prf==3, ip1y, NaN)");
    model.result().evaluationGroup("eg3").feature("ray1").set("unit", "mm");
    model.result().evaluationGroup("eg3").create("ray2", "Ray");
    model.result().evaluationGroup("eg3").feature("ray2").set("data", "ip1");
    model.result().evaluationGroup("eg3").feature("ray2").set("expr", "if(gop.prf==3, ip1x, NaN)");
    model.result().evaluationGroup("eg3").feature("ray2").set("unit", "mm");
    model.result().evaluationGroup("eg3").run();
    model.result().dataset().create("kde5", "KernelDensityEstimation");
    model.result().dataset("kde5").label("LSFx (eg3)");
    model.result().dataset("kde5").set("kerneltype", "parabolic");
    model.result().dataset("kde5").set("source", "evaluationgroup");
    model.result().dataset("kde5").set("evaluationgroup", "eg3");
    model.result().dataset("kde5").set("xaxisdata", "1");
    model.result().dataset("kde5").set("xvar", "eg3_x");
    model.result().dataset("kde5").set("outvar", "eg3_pdf_x");
    model.result().dataset().create("kde6", "KernelDensityEstimation");
    model.result().dataset("kde6").label("LSFy (eg3)");
    model.result().dataset("kde6").set("kerneltype", "parabolic");
    model.result().dataset("kde6").set("source", "evaluationgroup");
    model.result().dataset("kde6").set("evaluationgroup", "eg3");
    model.result().dataset("kde6").set("xaxisdata", "2");
    model.result().dataset("kde6").set("xvar", "eg3_x");
    model.result().dataset("kde6").set("outvar", "eg3_pdf_y");
    model.result().dataset().create("sfft5", "SpatialFFT");
    model.result().dataset("sfft5").label("MTFx (eg3)");
    model.result().dataset("sfft5").set("data", "kde5");
    model.result().dataset("sfft5").set("gridres", "manual");
    model.result().dataset("sfft5").set("sampresx", 32);
    model.result().dataset("sfft5").set("layout", "padding");
    model.result().dataset("sfft5").set("padx", 992);
    model.result().dataset("sfft5").set("maskdc", false);
    model.result().dataset("sfft5").set("normalizebydc", true);
    model.result().dataset("sfft5").set("fxvar", "eg3_fx");
    model.result().dataset().create("sfft6", "SpatialFFT");
    model.result().dataset("sfft6").label("MTFy (eg3)");
    model.result().dataset("sfft6").set("data", "kde6");
    model.result().dataset("sfft6").set("gridres", "manual");
    model.result().dataset("sfft6").set("sampresx", 32);
    model.result().dataset("sfft6").set("layout", "padding");
    model.result().dataset("sfft6").set("padx", 992);
    model.result().dataset("sfft6").set("maskdc", false);
    model.result().dataset("sfft6").set("normalizebydc", true);
    model.result().dataset("sfft6").set("fxvar", "eg3_fx");
    model.result("pg6").create("lngr5", "LineGraph");
    model.result("pg6").feature("lngr5").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr5").set("linewidth", "preference");
    model.result("pg6").feature("lngr5").set("data", "sfft5");
    model.result("pg6").feature("lngr5").set("expr", "abs(fft(eg3_pdf_x))");
    model.result("pg6").feature("lngr5").set("xdata", "expr");
    model.result("pg6").feature("lngr5").set("xdataexpr", "eg3_fx");
    model.result("pg6").feature("lngr5").set("legendmethod", "manual");
    model.result("pg6").feature("lngr5").setIndex("legends", "MTFx (relg3)", 0);
    model.result("pg6").feature("lngr5").label("MTFx (relg3)");
    model.result("pg6").feature("lngr5").set("legend", true);
    model.result("pg6").create("lngr6", "LineGraph");
    model.result("pg6").feature("lngr6").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr6").set("linewidth", "preference");
    model.result("pg6").feature("lngr6").set("data", "sfft6");
    model.result("pg6").feature("lngr6").set("expr", "abs(fft(eg3_pdf_y))");
    model.result("pg6").feature("lngr6").set("xdata", "expr");
    model.result("pg6").feature("lngr6").set("xdataexpr", "eg3_fx");
    model.result("pg6").feature("lngr6").set("legendmethod", "manual");
    model.result("pg6").feature("lngr6").setIndex("legends", "MTFy (relg3)", 0);
    model.result("pg6").feature("lngr6").label("MTFy (relg3)");
    model.result("pg6").feature("lngr6").set("legend", true);
    model.result().move("pg4", 3);
    model.result("pg4").tag("pg4");
    model.result().move("pg5", 4);
    model.result("pg5").tag("pg5");
    model.result().move("pg6", 5);
    model.result("pg6").tag("pg6");
    model.result().remove("pg3");
    model.result("pg5").label("Spot Diagram");
    model.result("pg5").tag("pg3");
    model.result().move("pg3", 2);
    model.result().move("pg4", 3);
    model.result("pg4").tag("pg4");
    model.result().move("pg6", 4);
    model.result("pg6").tag("pg5");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").feature("spot1").set("filterreleaseactive", true);
    model.result().dataset("ip1").set("genmethod", "pointnormal");
    model.result().dataset("ip1").setIndex("genpnpoint", "-2.648426438014424E-9[mm]", 0);
    model.result().dataset("ip1").setIndex("genpnpoint", "5.954144416984044E-9[mm]", 1);
    model.result().dataset("ip1").setIndex("genpnpoint", "161.7262387223244[mm]", 2);
    model.result().dataset("ip1").set("genpnvec", new double[]{-1.7747101673499457E-11, 7.75390072556921E-11, 1});
    model.result("pg3").feature("spot1").run();
    model.result().dataset("ip1").set("genmethod", "pointnormal");
    model.result().dataset("ip1").setIndex("genpnpoint", "2.086583169722705E-9[mm]", 0);
    model.result().dataset("ip1").setIndex("genpnpoint", "-4.881366864486811E-9[mm]", 1);
    model.result().dataset("ip1").setIndex("genpnpoint", "162.755168365997[mm]", 2);
    model.result().dataset("ip1").set("genpnvec", new double[]{7.758034099993539E-11, -1.0175780298548219E-10, 1});
    model.result("pg3").feature("spot1").run();
    model.result("pg3").set("ispendingzoom", true);
    model.result("pg3").feature("spot1").set("filterreleaseactive", false);
    model.result("pg3").run();
    model.result().dataset("ip1").setIndex("genpnpoint", 0, 0);
    model.result().dataset("ip1").setIndex("genpnpoint", 0, 1);
    model.result().dataset("ip1").set("genpnpoint", new String[]{"0", "0", "162.755[mm]"});
    model.result().dataset("ip1").setIndex("genpnvec", 0, 0);
    model.result().dataset("ip1").set("genpnvec", new int[]{0, 0, 1});
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg3").run();
    model.result("pg5").run();
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", 0);
    model.result("pg5").set("xmax", 125);
    model.result("pg5").run();

    model.param("par2").set("N_ring", "50");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg2").set("boxlengths", new double[]{72.5, 72.5, 158.56813378784963});
    model.result("pg2").feature("rtrj1").set("sphereradiusscale", 1);
    model.result("pg2").feature("rtrj1").set("sphereradiusscaleactive", false);
    model.result("pg2").feature("rtrj1").set("tailscale", 1);
    model.result("pg2").feature("rtrj1").set("tailscaleactive", false);
    model.result("pg2").feature("rtrj1").set("arrowscale", 0);
    model.result("pg2").feature("rtrj1").set("arrowscaleactive", false);
    model.result("pg2").feature("rtrj1").set("ellipsearrowscale", 1);
    model.result("pg2").feature("rtrj1").set("ellipsearrowscaleactive", false);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangeunit", "\u00b5m");
    model.result("pg2").feature("rtrj1").feature("col1").set("rangecolormin", 6.058286789306643E-5);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangecolormax", 8.523864580330587);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangecoloractive", "off");
    model.result("pg2").feature("rtrj1").feature("col1")
         .set("rangeactualminmax", new double[]{6.058286789306643E-5, 8.523864580330587});
    model.result("pg2").feature("rtrj1").feature("col1").set("rangeisshared", false);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangeminpositive", 6.058286789306643E-5);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangedatamin", 6.058286789306643E-5);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangedatamax", 8.523864580330587);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangedataactive", "off");
    model.result("pg2").feature("rtrj1").set("renderinfo", "1 Edge3D 1 1 4 1 Color");
    model.result("pg2").feature("rtrj1").set("boxlengths", new double[]{41.5, 53.10047912597656, 167.7769012451172});
    model.result("pg2").feature("surf1").set("rangeunit", "1");
    model.result("pg2").feature("surf1").set("rangecolormin", 1);
    model.result("pg2").feature("surf1").set("rangecolormax", 1);
    model.result("pg2").feature("surf1").set("rangecoloractive", "off");
    model.result("pg2").feature("surf1").set("rangeactualminmax", new double[]{1, 1});
    model.result("pg2").feature("surf1").set("rangeisshared", false);
    model.result("pg2").feature("surf1").set("rangeminpositive", 1);
    model.result("pg2").feature("surf1").set("rangedatamin", 1);
    model.result("pg2").feature("surf1").set("rangedatamax", 1);
    model.result("pg2").feature("surf1").set("rangedataactive", "off");
    model.result("pg2").feature("surf1").set("renderinfo", "1 Surface3D 1 1 0 1 Color");
    model.result("pg2").feature("surf1").set("boxlengths", new double[]{58, 58, 160.8213348388672});

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg4").set("boxlengths", new double[]{72.5, 72.5, 158.56813378784963});
    model.result("pg4").feature("rtrj1").set("sphereradiusscale", 1);
    model.result("pg4").feature("rtrj1").set("sphereradiusscaleactive", false);
    model.result("pg4").feature("rtrj1").set("tailscale", 1);
    model.result("pg4").feature("rtrj1").set("tailscaleactive", false);
    model.result("pg4").feature("rtrj1").set("arrowscale", 0);
    model.result("pg4").feature("rtrj1").set("arrowscaleactive", false);
    model.result("pg4").feature("rtrj1").set("ellipsearrowscale", 1);
    model.result("pg4").feature("rtrj1").set("ellipsearrowscaleactive", false);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangeunit", "s");
    model.result("pg4").feature("rtrj1").feature("col1").set("rangecolormin", 0);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangecolormax", 6.671281903963041E-10);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangecoloractive", "off");
    model.result("pg4").feature("rtrj1").feature("col1")
         .set("rangeactualminmax", new double[]{0, 6.671281903963041E-10});
    model.result("pg4").feature("rtrj1").feature("col1").set("rangeisshared", false);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangeminpositive", 1.6682755795801608E-11);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangedatamin", 0);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangedatamax", 6.671281903963041E-10);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangedataactive", "off");
    model.result("pg4").feature("rtrj1").set("renderinfo", "1 Edge3D 1 1 4 1 Color");
    model.result("pg4").feature("rtrj1").set("boxlengths", new double[]{41.5, 53.10047912597656, 167.7769012451172});
    model.result("pg3").feature("spot1").set("truesphereradiusscale", 0.2);
    model.result("pg3").feature("spot1")
         .set("labels", new String[]{"$r_{\\textrm{rms}} = \\textrm{2.10 \\textmu m}$", "$r_{\\textrm{rms}} = \\textrm{2.86 \\textmu m}$", "$r_{\\textrm{rms}} = \\textrm{4.12 \\textmu m}$"});
    model.result("pg3").feature("spot1").set("sphereradiusscale", 0.2);
    model.result("pg3").feature("spot1").set("sphereradiusscaleactive", false);
    model.result("pg3").feature("spot1")
         .set("renderinfo", "24 Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D 24 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color");
    model.result("pg3").feature("spot1").set("boxlengths", new double[]{0.03936637565493584, 0.04822846129536629});
    model.result("pg3").set("xlabel", "");
    model.result("pg3").set("ylabel", "");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg3").run();
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").run();

    model.param("par2").set("N_ring", "70");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg2").set("boxlengths", new double[]{72.5, 72.5, 158.56813378784963});
    model.result("pg2").feature("rtrj1").set("sphereradiusscale", 1);
    model.result("pg2").feature("rtrj1").set("sphereradiusscaleactive", false);
    model.result("pg2").feature("rtrj1").set("tailscale", 1);
    model.result("pg2").feature("rtrj1").set("tailscaleactive", false);
    model.result("pg2").feature("rtrj1").set("arrowscale", 0);
    model.result("pg2").feature("rtrj1").set("arrowscaleactive", false);
    model.result("pg2").feature("rtrj1").set("ellipsearrowscale", 1);
    model.result("pg2").feature("rtrj1").set("ellipsearrowscaleactive", false);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangeunit", "\u00b5m");
    model.result("pg2").feature("rtrj1").feature("col1").set("rangecolormin", 6.0361535389314846E-5);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangecolormax", 8.52203517337302);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangecoloractive", "off");
    model.result("pg2").feature("rtrj1").feature("col1")
         .set("rangeactualminmax", new double[]{6.0361535389314846E-5, 8.52203517337302});
    model.result("pg2").feature("rtrj1").feature("col1").set("rangeisshared", false);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangeminpositive", 6.0361535389314846E-5);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangedatamin", 6.0361535389314846E-5);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangedatamax", 8.52203517337302);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangedataactive", "off");
    model.result("pg2").feature("rtrj1").set("renderinfo", "1 Edge3D 1 1 4 1 Color");
    model.result("pg2").feature("rtrj1").set("boxlengths", new double[]{41.5, 53.10047912597656, 167.7769012451172});
    model.result("pg2").feature("surf1").set("rangeunit", "1");
    model.result("pg2").feature("surf1").set("rangecolormin", 1);
    model.result("pg2").feature("surf1").set("rangecolormax", 1);
    model.result("pg2").feature("surf1").set("rangecoloractive", "off");
    model.result("pg2").feature("surf1").set("rangeactualminmax", new double[]{1, 1});
    model.result("pg2").feature("surf1").set("rangeisshared", false);
    model.result("pg2").feature("surf1").set("rangeminpositive", 1);
    model.result("pg2").feature("surf1").set("rangedatamin", 1);
    model.result("pg2").feature("surf1").set("rangedatamax", 1);
    model.result("pg2").feature("surf1").set("rangedataactive", "off");
    model.result("pg2").feature("surf1").set("renderinfo", "1 Surface3D 1 1 0 1 Color");
    model.result("pg2").feature("surf1").set("boxlengths", new double[]{58, 58, 160.8213348388672});
    model.result("pg4").set("boxlengths", new double[]{72.5, 72.5, 158.56813378784963});
    model.result("pg4").feature("rtrj1").set("sphereradiusscale", 1);
    model.result("pg4").feature("rtrj1").set("sphereradiusscaleactive", false);
    model.result("pg4").feature("rtrj1").set("tailscale", 1);
    model.result("pg4").feature("rtrj1").set("tailscaleactive", false);
    model.result("pg4").feature("rtrj1").set("arrowscale", 0);
    model.result("pg4").feature("rtrj1").set("arrowscaleactive", false);
    model.result("pg4").feature("rtrj1").set("ellipsearrowscale", 1);
    model.result("pg4").feature("rtrj1").set("ellipsearrowscaleactive", false);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangeunit", "s");
    model.result("pg4").feature("rtrj1").feature("col1").set("rangecolormin", 0);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangecolormax", 6.671281903963041E-10);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangecoloractive", "off");
    model.result("pg4").feature("rtrj1").feature("col1")
         .set("rangeactualminmax", new double[]{0, 6.671281903963041E-10});
    model.result("pg4").feature("rtrj1").feature("col1").set("rangeisshared", false);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangeminpositive", 1.6682755795801608E-11);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangedatamin", 0);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangedatamax", 6.671281903963041E-10);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangedataactive", "off");
    model.result("pg4").feature("rtrj1").set("renderinfo", "1 Edge3D 1 1 4 1 Color");
    model.result("pg4").feature("rtrj1").set("boxlengths", new double[]{41.5, 53.10047912597656, 167.7769012451172});
    model.result("pg3").feature("spot1").set("truesphereradiusscale", 0.2);
    model.result("pg3").feature("spot1")
         .set("labels", new String[]{"$r_{\\textrm{rms}} = \\textrm{2.10 \\textmu m}$", "$r_{\\textrm{rms}} = \\textrm{2.86 \\textmu m}$", "$r_{\\textrm{rms}} = \\textrm{4.10 \\textmu m}$"});
    model.result("pg3").feature("spot1").set("sphereradiusscale", 0.2);
    model.result("pg3").feature("spot1").set("sphereradiusscaleactive", false);
    model.result("pg3").feature("spot1")
         .set("renderinfo", "27 Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D 27 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color");
    model.result("pg3").feature("spot1").set("boxlengths", new double[]{0.03929924592375755, 0.04812914878129959});
    model.result("pg3").set("xlabel", "");
    model.result("pg3").set("ylabel", "");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg3").run();
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").run();

    model.param("par2").set("N_ring", "100");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg2").set("boxlengths", new double[]{72.5, 72.5, 158.56813378784963});
    model.result("pg2").feature("rtrj1").set("sphereradiusscale", 1);
    model.result("pg2").feature("rtrj1").set("sphereradiusscaleactive", false);
    model.result("pg2").feature("rtrj1").set("tailscale", 1);
    model.result("pg2").feature("rtrj1").set("tailscaleactive", false);
    model.result("pg2").feature("rtrj1").set("arrowscale", 0);
    model.result("pg2").feature("rtrj1").set("arrowscaleactive", false);
    model.result("pg2").feature("rtrj1").set("ellipsearrowscale", 1);
    model.result("pg2").feature("rtrj1").set("ellipsearrowscaleactive", false);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangeunit", "\u00b5m");
    model.result("pg2").feature("rtrj1").feature("col1").set("rangecolormin", 6.0384182511991804E-5);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangecolormax", 8.517614163898466);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangecoloractive", "off");
    model.result("pg2").feature("rtrj1").feature("col1")
         .set("rangeactualminmax", new double[]{6.0384182511991804E-5, 8.517614163898466});
    model.result("pg2").feature("rtrj1").feature("col1").set("rangeisshared", false);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangeminpositive", 6.0384182511991804E-5);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangedatamin", 6.0384182511991804E-5);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangedatamax", 8.517614163898466);
    model.result("pg2").feature("rtrj1").feature("col1").set("rangedataactive", "off");
    model.result("pg2").feature("rtrj1").set("renderinfo", "1 Edge3D 1 1 4 1 Color");
    model.result("pg2").feature("rtrj1").set("boxlengths", new double[]{41.5, 53.10047912597656, 167.7769012451172});
    model.result("pg2").feature("surf1").set("rangeunit", "1");
    model.result("pg2").feature("surf1").set("rangecolormin", 1);
    model.result("pg2").feature("surf1").set("rangecolormax", 1);
    model.result("pg2").feature("surf1").set("rangecoloractive", "off");
    model.result("pg2").feature("surf1").set("rangeactualminmax", new double[]{1, 1});
    model.result("pg2").feature("surf1").set("rangeisshared", false);
    model.result("pg2").feature("surf1").set("rangeminpositive", 1);
    model.result("pg2").feature("surf1").set("rangedatamin", 1);
    model.result("pg2").feature("surf1").set("rangedatamax", 1);
    model.result("pg2").feature("surf1").set("rangedataactive", "off");
    model.result("pg2").feature("surf1").set("renderinfo", "1 Surface3D 1 1 0 1 Color");
    model.result("pg2").feature("surf1").set("boxlengths", new double[]{58, 58, 160.8213348388672});
    model.result("pg4").set("boxlengths", new double[]{72.5, 72.5, 158.56813378784963});
    model.result("pg4").feature("rtrj1").set("sphereradiusscale", 1);
    model.result("pg4").feature("rtrj1").set("sphereradiusscaleactive", false);
    model.result("pg4").feature("rtrj1").set("tailscale", 1);
    model.result("pg4").feature("rtrj1").set("tailscaleactive", false);
    model.result("pg4").feature("rtrj1").set("arrowscale", 0);
    model.result("pg4").feature("rtrj1").set("arrowscaleactive", false);
    model.result("pg4").feature("rtrj1").set("ellipsearrowscale", 1);
    model.result("pg4").feature("rtrj1").set("ellipsearrowscaleactive", false);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangeunit", "s");
    model.result("pg4").feature("rtrj1").feature("col1").set("rangecolormin", 0);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangecolormax", 6.671281903963041E-10);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangecoloractive", "off");
    model.result("pg4").feature("rtrj1").feature("col1")
         .set("rangeactualminmax", new double[]{0, 6.671281903963041E-10});
    model.result("pg4").feature("rtrj1").feature("col1").set("rangeisshared", false);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangeminpositive", 1.6682755795801608E-11);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangedatamin", 0);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangedatamax", 6.671281903963041E-10);
    model.result("pg4").feature("rtrj1").feature("col1").set("rangedataactive", "off");
    model.result("pg4").feature("rtrj1").set("renderinfo", "1 Edge3D 1 1 4 1 Color");
    model.result("pg4").feature("rtrj1").set("boxlengths", new double[]{41.5, 53.10047912597656, 167.7769012451172});
    model.result("pg3").feature("spot1").set("truesphereradiusscale", 0.2);
    model.result("pg3").feature("spot1")
         .set("labels", new String[]{"$r_{\\textrm{rms}} = \\textrm{2.10 \\textmu m}$", "$r_{\\textrm{rms}} = \\textrm{2.86 \\textmu m}$", "$r_{\\textrm{rms}} = \\textrm{4.10 \\textmu m}$"});
    model.result("pg3").feature("spot1").set("sphereradiusscale", 0.2);
    model.result("pg3").feature("spot1").set("sphereradiusscaleactive", false);
    model.result("pg3").feature("spot1")
         .set("renderinfo", "30 Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D Sphere2D Marker2D Edge2D 30 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color 2 0 1 2 Color Radius 0 0 1 0 1 Color");
    model.result("pg3").feature("spot1").set("boxlengths", new double[]{0.039370592683553696, 0.04823296144604683});
    model.result("pg3").set("xlabel", "");
    model.result("pg3").set("ylabel", "");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg3").run();
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").run();

    model.title("Petzval \u900f\u955c\u51e0\u4f55\u8c03\u5236\u4f20\u9012\u51fd\u6570");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201cApp \u65b9\u6cd5\u201d\u6765\u8ba1\u7b97\u548c\u7ed8\u5236 Petzval \u900f\u955c\u7684\u51e0\u4f55\u201c\u8c03\u5236\u4f20\u9012\u51fd\u6570\u201d(MTF)\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("petzval_lens_geometric_modulation_transfer_function.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
