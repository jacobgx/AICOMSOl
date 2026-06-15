/*
 * turbine_stator_creep.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:28 by COMSOL 6.3.0.290. */
public class turbine_stator_creep {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Creep");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 3);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/te1", true);

    model.component("comp1").view("view1").set("showgrid", false);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Pr_cool", "0.72", "Cooling Prandtl number");
    model.param().set("U_suction_side", "450[m/s]", "Gas velocity on stator suction side");
    model.param().set("U_pressure_side", "300[m/s]", "Gas velocity on stator pressure side");
    model.param().set("U_platform", "350[m/s]", "Gas velocity along platform walls");
    model.param().set("T_gas", "1150[K]", "Gas temperature");
    model.param().set("p_high", "30[bar]", "High pressure level");
    model.param().set("mu_cool", "3.1e-5[Pa*s]", "Viscosity of the cooling air");
    model.param().set("Cp_cool", "770[J/kg/K]", "Heat capacity of the cooling air");
    model.param().set("T_cool", "650[K]", "Cooling air temperature");
    model.param().set("H_cool", "0.01[m]", "Characteristic length scale of cooling channels");
    model.param().set("T_work", "900[K]", "Working temperature");
    model.param().set("Nu_cool", "400", "Average Nusselt number in cooling channel");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "turbine_stator.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("Cooling Duct");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1")
         .set(59, 60, 61, 62, 63, 64, 66, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114);

    model.component("comp1").view("view1").set("transparency", false);
    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("Exchange Boundaries");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2")
         .set(1, 2, 3, 4, 9, 11, 14, 16, 17, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 36, 37, 38, 39, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 65, 67, 68, 69, 70, 71, 72, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("GTD111 DS [solid,longitudinal]");
    model.component("comp1").material("mat1").info().create("Composition");
    model.component("comp1").material("mat1").info("Composition")
         .body("bal. Ni, (13.7-14.3) Cr, (9-10) Co, (2.8-3.2) Al, (4.7-5.1) Ti, (3.5-4.1) W, (2.5-3.1) Ta, (0.08-0.12) C, (0.005-0.04) Zr, (0.005-0.02) B, 0.35 Fe max, 0.30 Si max, 0.10 Mn max, 0.10 Cu max, 0.015 P max, 0.005 S max (wt %)");
    model.component("comp1").material("mat1").info("Composition").title("Composition");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", "k(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "Reference: Chromalloy Gas Turbine LLC. http://www.chromalloy.com/\nNote: calculated from the thermal conductivity, density, and specific heat, solution heat-treated at 1121 \u00b0C (1394 K) for 2 h, aged at 843 \u00b0C (1116 K) for 24 h");
    model.component("comp1").material("mat1").propertyGroup("def").set("Syt", "Syt(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("Syt", "Reference: Chromalloy Gas Turbine LLC. http://www.chromalloy.com/\nNote: tested at an unknown strain rate in tension, longitudinal direction, average of 2 samples, 0.05 in\u00b2 (0.32 cm\u00b2) cross-sectional area, spread in the measurements was approximately 10 MPa (1.45 ksi) at 427 \u00b0C (700 K) and 120 MPa (17.4 ksi) at 982 \u00b0C (1255 K), solution heat-treated at 1121 \u00b0C (1394 K) for 2 h, aged at 843 \u00b0C (1116 K) for 24 h");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", "(alpha(T)+(Tempref-293[K])*if(abs(T-Tempref)>1e-3,(alpha(T)-alpha(Tempref))/(T-Tempref),d(alpha(T),T)))/(1+alpha(Tempref)*(Tempref-293[K]))");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: Chromalloy Gas Turbine LLC. http://www.chromalloy.com/\nNote: the reference temperature is 20 \u00b0C (293 K), solution heat-treated at 1121 \u00b0C (1394 K) for 2 h, aged at 843 \u00b0C (1116 K) for 24 h, calculated from the linear expansion\nReference temperature: 293.00[K]");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "C(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "Reference: Chromalloy Gas Turbine LLC. http://www.chromalloy.com/\nNote: average of 3 samples with a differential scanning calorimeter in argon ASTM E1269, heating rate was 20 \u00b0C/min, scatter in data was 3% at the highest temperatures, solution heat-treated at 1121 \u00b0C (1394 K) for 2 h, aged at 843 \u00b0C (1116 K) for 24 h");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "Reference: Chromalloy Gas Turbine LLC. http://www.chromalloy.com/\nNote: solution heat-treated at 1121 \u00b0C (1394 K) for 2 h, aged at 843 \u00b0C (1116 K) for 24 h, calculated from the linear expansion and the room temperature density");
    model.component("comp1").material("mat1").propertyGroup("def").set("TD", "TD(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("TD", "Reference: Chromalloy Gas Turbine LLC. http://www.chromalloy.com/\nNote: average of 3 samples with a differential scanning calorimeter in argon ASTM, scatter in data was 6%, solution heat-treated at 1121 \u00b0C (1394 K) for 2 h, aged at 843 \u00b0C (1116 K) for 24 h");
    model.component("comp1").material("mat1").propertyGroup("def").set("elong", "elong(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("elong", "Reference: Chromalloy Gas Turbine LLC. http://www.chromalloy.com/\nNote: tested at an unknown strain rate in tension, longitudinal direction, average of 2 samples, 0.05 in\u00b2 (0.32 cm\u00b2) cross-sectional area, spread in the measurements was approximately 9% at 982 \u00b0C (1255 K), solution heat-treated at 1121 \u00b0C (1394 K) for 2 h, aged at 843 \u00b0C (1116 K) for 24 h");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("funcname", "k");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"293.0", "1255.41", "1.672554+0.03576171*T^1-5.557891E-5*T^2+5.531542E-8*T^3-2.026391E-11*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Syt", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("Syt").set("funcname", "Syt");
    model.component("comp1").material("mat1").propertyGroup("def").func("Syt").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Syt").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("Syt")
         .set("pieces", new String[][]{{"700.0", "1255.41", "6.949496E8+343399.5*T^1+1301.727*T^2-1.403259*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Syt").label("Piecewise 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("Syt").set("fununit", "Pa");
    model.component("comp1").material("mat1").propertyGroup("def").func("Syt").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("alpha", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("funcname", "alpha");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha")
         .set("pieces", new String[][]{{"293.0", "1255.41", "1.132769E-5-6.760265E-10*T^1+1.107791E-11*T^2-1.36022E-14*T^3+5.799942E-18*T^4-4.733165E-30*T^5"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("C", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("C").set("funcname", "C");
    model.component("comp1").material("mat1").propertyGroup("def").func("C").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("C").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("C")
         .set("pieces", new String[][]{{"293.0", "1255.41", "323.7180465+0.4578902656*T^1-4.3938276E-4*T^2+2.20846959E-7*T^3-4.459123104E-11*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("C").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("C").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("C").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("funcname", "rho");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"293.0", "1255.41", "8375.668-0.2799683*T^1+6.630845E-5*T^2-3.116057E-7*T^3+3.316263E-10*T^4-1.286122E-13*T^5"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("TD", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("TD").set("funcname", "TD");
    model.component("comp1").material("mat1").propertyGroup("def").func("TD").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("TD").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("TD")
         .set("pieces", new String[][]{{"293.0", "1255.41", "1.079217E-6+7.346752E-9*T^1-1.251635E-11*T^2+1.288298E-14*T^3-4.743877E-18*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("TD").label("Piecewise 5");
    model.component("comp1").material("mat1").propertyGroup("def").func("TD").set("fununit", "m^2/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("TD").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("elong", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("elong").set("funcname", "elong");
    model.component("comp1").material("mat1").propertyGroup("def").func("elong").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("elong").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("elong")
         .set("pieces", new String[][]{{"700.0", "1255.41", "54.23787-0.1190233*T^1+7.722161E-5*T^2"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("elong").label("Piecewise 6");
    model.component("comp1").material("mat1").propertyGroup("def").func("elong").set("fununit", "");
    model.component("comp1").material("mat1").propertyGroup("def").func("elong").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("strainreferencetemperature");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ThermalExpansion", "ThermalExpansion", "Thermal expansion");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion")
         .set("dL", "(dL(T)-dL(Tempref))/(1+dL(Tempref))");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion")
         .setPropertyInfo("dL", "Reference: Chromalloy Gas Turbine LLC. http://www.chromalloy.com/\nNote: the reference temperature is 20 \u00b0C (293 K), average of 3 samples measured on heating, values on cooling were slightly different, push-rod dilatometer ASTM E228, measured in helium at a 2 \u00b0C/min heating rate, solution heat-treated at 1121 \u00b0C (1394 K) for 2 h, aged at 843 \u00b0C (1116 K) for 24 h\nReference temperature: 293.00[K]");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func().create("dL", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL").set("funcname", "dL");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL")
         .set("pieces", new String[][]{{"293.0", "1255.41", "-0.00331901441+1.152577E-5*T^1-3.921853E-9*T^2+1.506335E-11*T^3-1.530158E-14*T^4+5.799942E-18*T^5"}});
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL").set("fununit", "");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion")
         .addInput("strainreferencetemperature");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E_solid_longitudinal_1(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: Chromalloy Gas Turbine LLC. http://www.chromalloy.com/\nNote: dynamic modulus in longitudinal direction, solution heat-treated at 1121 \u00b0C (1394 K) for 2 h, aged at 843 \u00b0C (1116 K) for 24 h");
    model.component("comp1").material("mat1").propertyGroup("Enu").func()
         .create("E_solid_longitudinal_1", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E_solid_longitudinal_1")
         .set("funcname", "E_solid_longitudinal_1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E_solid_longitudinal_1").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E_solid_longitudinal_1")
         .set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E_solid_longitudinal_1")
         .set("pieces", new String[][]{{"293.0", "871.0", "1.273228E11-2.03861E7*T^1+15877.53*T^2-21.02714*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E_solid_longitudinal_1").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E_solid_longitudinal_1")
         .set("fununit", "Pa");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E_solid_longitudinal_1")
         .set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("lighting", "cooktorrance");
    model.component("comp1").material("mat1").set("specular", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.1);
    model.component("comp1").material("mat1").set("metallic", 0);
    model.component("comp1").material("mat1").set("pearl", 0);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("clearcoat", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").set("shininess", 130);
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.33"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E_solid_longitudinal_1")
         .set("extrap", "interior");

    model.common("cminpt").set("modified", new String[][]{{"strainreferencetemperature", "310[K]"}});

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_gas");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("sel2");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 25);
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_work");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2").selection().named("sel1");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "Nu_cool*mu_cool*Cp_cool/2/Pr_cool/H_cool");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "T_cool");
    model.component("comp1").physics("ht").create("hf3", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf3").selection().set(41);
    model.component("comp1").physics("ht").feature("hf3").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf3").set("HeatTransferCoefficientType", "ExtForcedConvection");
    model.component("comp1").physics("ht").feature("hf3")
         .set("ExtForcedConvectionType", "PlateLocalTransferCoefficient");
    model.component("comp1").physics("ht").feature("hf3").set("x_pl", "0.1675-x");
    model.component("comp1").physics("ht").feature("hf3").set("U", "U_suction_side");
    model.component("comp1").physics("ht").feature("hf3").set("pA", "p_high");
    model.component("comp1").physics("ht").feature("hf3").set("Text", "T_gas");
    model.component("comp1").physics("ht").create("hf4", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf4").selection().set(40);
    model.component("comp1").physics("ht").feature("hf4").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf4").set("HeatTransferCoefficientType", "ExtForcedConvection");
    model.component("comp1").physics("ht").feature("hf4")
         .set("ExtForcedConvectionType", "PlateLocalTransferCoefficient");
    model.component("comp1").physics("ht").feature("hf4").set("x_pl", "0.1675-x");
    model.component("comp1").physics("ht").feature("hf4").set("U", "U_pressure_side");
    model.component("comp1").physics("ht").feature("hf4").set("pA", "p_high");
    model.component("comp1").physics("ht").feature("hf4").set("Text", "T_gas");
    model.component("comp1").physics("ht").create("hf5", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf5").selection().set(10, 15, 32, 33, 34, 35);
    model.component("comp1").physics("ht").feature("hf5").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf5").set("HeatTransferCoefficientType", "ExtForcedConvection");
    model.component("comp1").physics("ht").feature("hf5")
         .set("ExtForcedConvectionType", "PlateLocalTransferCoefficient");
    model.component("comp1").physics("ht").feature("hf5").set("x_pl", "0.19-x");
    model.component("comp1").physics("ht").feature("hf5").set("U", "U_platform");
    model.component("comp1").physics("ht").feature("hf5").set("pA", "p_high");
    model.component("comp1").physics("ht").feature("hf5").set("Text", "T_gas");
    model.component("comp1").physics("solid").create("spf1", "SpringFoundation2", 2);
    model.component("comp1").physics("solid").feature("spf1").selection().set(42, 43, 56, 129, 131);
    model.component("comp1").physics("solid").feature("spf1")
         .set("kPerArea", new String[]{"1e9", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").create("spf2", "SpringFoundation2", 2);
    model.component("comp1").physics("solid").feature("spf2").selection().set(27, 29, 53, 70);
    model.component("comp1").physics("solid").feature("spf2")
         .set("kPerArea", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "1e9"});
    model.component("comp1").physics("solid").create("spf3", "SpringFoundation2", 2);
    model.component("comp1").physics("solid").feature("spf3").selection().set(6, 12);
    model.component("comp1").physics("solid").feature("spf3")
         .set("kPerArea", new String[]{"0", "0", "0", "0", "1e9", "0", "0", "0", "0"});

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.025);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.0025);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 2);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.75);
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("size1").selection()
         .set(40, 41, 59, 60, 61, 62, 63, 64, 66, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114);

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 0.0035);
    model.component("comp1").mesh("mesh1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("se1").set("segterm", "iter");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subtermconst", "tol");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subntolfact", 1);
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subtermconst", "tol");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subntolfact", 1);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("Stress (solid)");
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
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "Displacement field");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Temperature (ht)");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "T");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("def").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").create("mrkr1", "Marker");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("mrkr1").set("display", "max");
    model.result("pg1").feature("vol1").feature("mrkr1").set("precision", 3);
    model.result("pg1").feature("vol1").feature("mrkr1").set("backgroundcolor", "fromtheme");
    model.result("pg1").feature("vol1").feature("mrkr1").set("showframe", true);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("tran1").set("transparency", 0.2);
    model.result("pg1").feature("vol1").feature("tran1").set("uniformblending", 0.5);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature("vol1").create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").feature("tran1").set("transparency", 0.2);
    model.result("pg2").feature("vol1").feature("tran1").set("uniformblending", 0.5);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("Displacement");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "solid.disp");
    model.result("pg3").feature("surf1").set("descr", "Displacement magnitude");
    model.result("pg3").feature("surf1").set("unit", "mm");
    model.result("pg3").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u6da1\u8f6e\u9759\u53f6\u7247\u7684\u70ed\u5e94\u529b\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u70ed\u5e94\u529b\uff0c\u5b9e\u4f53\u201d\u63a5\u53e3\u8ba1\u7b97\u6da1\u8f6e\u9759\u53f6\u7247\u7684\u70ed\u81f4\u5e94\u529b\u3002");

    model.label("turbine_stator.mph");

    model.result("pg3").run();

    model.component("comp1").physics("solid").feature("lemm1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").feature("lemm1").create("cmm1", "Creep2", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("thermalEffects", "Arrhenius");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("T0", "T_work");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("Q", "350[kJ/mol]");

    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", new String[]{"2.5e-23[1/h]"});
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", new String[]{"1[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", new String[]{"6.75"});

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").set("type", "maximum");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").probe("dom1").set("expr", "solid.eceGp");
    model.component("comp1").probe("dom1").set("descractive", true);

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "0 10 100 400");
    model.study("std1").feature("time").setSolveFor("/physics/ht", false);

    model.sol("sol1").createAutoSequence("std1");
    model.sol("sol1").feature("s1").feature("se1").set("segterm", "iter");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subtermconst", "tol");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subntolfact", 1);
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subtermconst", "tol");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subntolfact", 1);
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", 0.1);

    model.study("std1").createAutoSequences("sol");

    model.sol("sol1").copySolution("sol2");

    model.component("comp1").probe("dom1").genResult("none");

    model.sol("sol1").runFromTo("st2", "v2");

    model.result("pg1").run();
    model.result("pg4").set("window", "window1");
    model.result("pg4").run();
    model.result("pg4").label("\u7b49\u6548\u8815\u53d8\u5e94\u53d8 vs. \u65f6\u95f4");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u7b49\u6548\u8815\u53d8\u5e94\u53d8 (1)");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").set("window", "window1");
    model.result("pg4").run();
    model.result("pg4").feature("tblp1").set("linemarker", "circle");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("dom1").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").label("\u7b49\u6548\u8815\u53d8\u5e94\u53d8");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").set("expr", "solid.eceGp");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").set("rangecoloractive", true);
    model.result("pg5").feature("vol1").set("rangecolormax", 0.005);
    model.result("pg5").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("vol1").set("colorcalibration", "-.7");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").feature("mrkr1").set("precision", 2);
    model.result("pg5").run();
    model.result("pg4").set("window", "window1");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").feature("vol1").feature("mrkr1").active(false);
    model.result("pg5").run();
    model.result("pg5").feature("vol1").feature("tran1").active(false);
    model.result("pg5").run();
    model.result("pg5").feature("vol1").feature("tran1").active(true);
    model.result("pg5").run();
    model.result("pg5").feature("vol1").feature("mrkr1").active(true);
    model.result("pg5").run();
    model.result("pg5").run();

    model.title("\u6da1\u8f6e\u9759\u53f6\u7247\u7684\u8815\u53d8\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u6da1\u8f6e\u9759\u53f6\u7247\u6b21\u7ea7\u8815\u53d8\u5f15\u8d77\u7684\u53d8\u5f62\u3002\u8815\u53d8\u7387\u53d7\u6e29\u5ea6\u7684\u5f71\u54cd\u5f88\u5927\uff0c\u56e0\u6b64\u53d8\u5f62\u548c\u5e94\u529b\u677e\u5f1b\u7531\u8ba1\u7b97\u5f97\u5230\u7684\u6e29\u5ea6\u573a\u63a7\u5236\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("turbine_stator_creep.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
