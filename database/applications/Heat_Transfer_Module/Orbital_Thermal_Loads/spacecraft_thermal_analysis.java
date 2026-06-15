/*
 * spacecraft_thermal_analysis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:24 by COMSOL 6.3.0.290. */
public class spacecraft_thermal_analysis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Orbital_Thermal_Loads");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("otl", "OrbitalThermalLoadsEvents", "geom1");
    model.component("comp1").physics().create("ev", "Events", "geom1");

    model.component("comp1").multiphysics().create("htrad1", "HeatTransferWithSurfaceToSurfaceRadiation", 2);
    model.component("comp1").multiphysics("htrad1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("htrad1").set("Rad_physics", "otl");
    model.component("comp1").multiphysics("htrad1").selection().all();

    model.study().create("std1");
    model.study("std1").create("otl", "OrbitThermalLoads");
    model.study("std1").feature("otl").set("solnum", "auto");
    model.study("std1").feature("otl").set("notsolnum", "auto");
    model.study("std1").feature("otl").set("outputmap", new String[]{});
    model.study("std1").feature("otl").setSolveFor("/physics/ht", true);
    model.study("std1").feature("otl").setSolveFor("/physics/otl", true);
    model.study("std1").feature("otl").setSolveFor("/physics/ev", true);
    model.study("std1").feature("otl").setSolveFor("/multiphysics/htrad1", true);
    model.study("std1").create("ot", "OrbitalTemperature");
    model.study("std1").feature("ot").set("solnum", "auto");
    model.study("std1").feature("ot").set("notsolnum", "auto");
    model.study("std1").feature("ot").set("outputmap", new String[]{});
    model.study("std1").feature("ot").setSolveFor("/physics/ht", true);
    model.study("std1").feature("ot").setSolveFor("/physics/otl", true);
    model.study("std1").feature("ot").setSolveFor("/physics/ev", true);
    model.study("std1").feature("ot").setSolveFor("/multiphysics/htrad1", true);

    model.param().set("Efficiency", "0.3");
    model.param().descr("Efficiency", "\u592a\u9633\u80fd\u7535\u6c60\u6548\u7387");

    model.component("comp1").geom("geom1").insertFile("spacecraft_thermal_analysis_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6240\u6709\u8f90\u5c04\u8fb9\u754c");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection("sel1").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(0);
    model.component("comp1").selection("sel2").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel2").set(0);
    model.component("comp1").selection("sel2").label("\u9762\u5411\u7a7a\u95f4\u7684\u8fb9\u754c");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u592a\u9633\u80fd\u7535\u6c60");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(5, 12, 39, 83);

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").label("\u6240\u6709\u57df\u7684\u6700\u5927\u503c");
    model.component("comp1").cpl("maxop1").selection().all();
    model.component("comp1").cpl().create("minop1", "Minimum");
    model.component("comp1").cpl("minop1").label("\u6240\u6709\u57df\u7684\u6700\u5c0f\u503c");
    model.component("comp1").cpl("minop1").selection().all();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("FR4 (Circuit Board)");
    model.component("comp1").material("mat3").set("family", "pcb");
    model.component("comp1").material("mat3").set("color", "custom");
    model.component("comp1").material("mat3").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "22[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.15");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat4").label("Silicon");
    model.component("comp1").material("mat4").set("family", "custom");
    model.component("comp1").material("mat4").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat4").set("diffuse", "custom");
    model.component("comp1").material("mat4")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat4").set("ambient", "custom");
    model.component("comp1").material("mat4")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat4").set("noise", true);
    model.component("comp1").material("mat4").set("fresnel", 0.7);
    model.component("comp1").material("mat4").set("roughness", 0.5);
    model.component("comp1").material("mat4").set("diffusewrap", 0);
    model.component("comp1").material("mat4").set("reflectance", 0);
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "170[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat4").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat4").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").label("Titanium beta-21S");
    model.component("comp1").material("mat5").set("family", "titanium");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "710[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "4940[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "105[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat2").selection().set(8, 10);
    model.component("comp1").material("mat3").selection().set(2, 3, 4, 5, 6, 7, 11);
    model.component("comp1").material("mat4").selection().geom("geom1", 2);
    model.component("comp1").material("mat4").selection().named("sel3");
    model.component("comp1").material("mat5").selection().set(9);

    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().set(8);
    model.component("comp1").physics("ht").feature("hs1").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs1").set("P0", "0.5[W]+4.5[W]*HS1");
    model.component("comp1").physics("ht").create("hs2", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs2").selection().set(10);
    model.component("comp1").physics("ht").feature("hs2").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs2").set("P0", "1[W]");
    model.component("comp1").physics("ht").create("sls1", "SolidLayeredShell", 2);
    model.component("comp1").physics("ht").feature("sls1").selection().named("sel3");
    model.component("comp1").physics("ht").feature("sls1").set("LayerType", "General");

    model.component("comp1").material("mat4").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat4").propertyGroup("shell").set("lth", new String[]{"0.5[mm]"});
    model.component("comp1").material("mat4").set("middlePlane", "bottom");

    model.component("comp1").physics("ht").create("tc1", "ThermalContact", 2);
    model.component("comp1").physics("ht").feature("tc1").selection().set(43, 47, 67);
    model.component("comp1").physics("ht").feature("tc1").set("ContactModel", "EquThinLayer");
    model.component("comp1").physics("ht").feature("tc1").set("Req", "0.01[K*m^2/W]");
    model.component("comp1").physics("ht").feature("tc1").set("hrad", "0[W/(m^2*K)]");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("sel3");
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "-otl.Grad_band1*Efficiency");
    model.component("comp1").physics("otl").selection().named("sel1");
    model.component("comp1").physics("otl").feature("plp1").set("longType", "spacecraft");
    model.component("comp1").physics("otl").feature("plp1").set("planetAlbedoTypeSolAmb", "userdefDistribution");
    model.component("comp1").physics("otl").feature("plp1").set("planetAlbedoSolAmb", 0.3);
    model.component("comp1").physics("otl").feature("plp1").set("planetRadiativeFluxTypeSolAmb", "userdefBand");
    model.component("comp1").physics("otl").feature("plp1")
         .setIndex("planetRadiativeFluxEachBandSolAmb", "225[W/m^2]", 1, 0);
    model.component("comp1").physics("otl").feature("op1").set("orbitType", "circular");
    model.component("comp1").physics("otl").feature("op1").set("R_orbit", "otl.R_planet+400[km]");
    model.component("comp1").physics("otl").feature("op1").set("i_orbit", "50[deg]");
    model.component("comp1").physics("otl").feature("op1").set("omegaType", "ltan");
    model.component("comp1").physics("otl").feature("op1").set("omegaTime_orbit", "15[h]");
    model.component("comp1").physics("otl").feature("so1").set("primaryRotation", "rate");
    model.component("comp1").physics("otl").feature("so1").set("primaryAngularRate", "2*360[deg]/otl.T_orbit");
    model.component("comp1").physics("otl").feature("dsurf1")
         .label("\u6f2b\u53cd\u5c04\u8868\u9762\uff0c\u5185\u4fa7");
    model.component("comp1").physics("otl").feature("dsurf1").set("epsilon_radSolAmb_mat", "userdef");
    model.component("comp1").physics("otl").feature("dsurf1").set("epsilon_radSolAmb", 0.8);
    model.component("comp1").physics("otl").create("dsurf2", "DiffuseSurface", 2);
    model.component("comp1").physics("otl").feature("dsurf2").selection().named("sel2");
    model.component("comp1").physics("otl").feature("dsurf2")
         .label("\u6f2b\u53cd\u5c04\u8868\u9762\uff0c\u5916\u4fa7");
    model.component("comp1").physics("otl").feature("dsurf2").set("epsilon_radSolAmb_mat", "userdefBand");
    model.component("comp1").physics("otl").feature("dsurf2").setIndex("epsilon_rad_bandSolAmb", 0.2, 0, 0);
    model.component("comp1").physics("otl").feature("dsurf2").setIndex("epsilon_rad_bandSolAmb", 0.85, 1, 0);
    model.component("comp1").physics("otl").create("dsurf3", "DiffuseSurface", 2);
    model.component("comp1").physics("otl").feature("dsurf3").selection().named("sel3");
    model.component("comp1").physics("otl").feature("dsurf3")
         .label("\u6f2b\u53cd\u5c04\u8868\u9762\uff0c\u592a\u9633\u80fd\u7535\u6c60");
    model.component("comp1").physics("otl").feature("dsurf3").set("epsilon_radSolAmb_mat", "userdefBand");
    model.component("comp1").physics("otl").feature("dsurf3").setIndex("epsilon_rad_bandSolAmb", 0.99, 0, 0);
    model.component("comp1").physics("otl").feature("dsurf3").setIndex("epsilon_rad_bandSolAmb", 0.95, 1, 0);
    model.component("comp1").physics("ev").create("ds1", "DiscreteStates", -1);
    model.component("comp1").physics("ev").feature("ds1").label("\u4eea\u5668 1\uff0c\u72b6\u6001\u53d8\u91cf");
    model.component("comp1").physics("ev").feature("ds1").setIndex("dim", "HS1", 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").feature("ds1")
         .setIndex("dimDescr", "\u70ed\u6e90\u6307\u793a\u5668", 0, 0);
    model.component("comp1").physics("ev").create("expl1", "ExplicitEvent", -1);
    model.component("comp1").physics("ev").feature("expl1").label("\u4eea\u5668 1\uff0c\u6253\u5f00");
    model.component("comp1").physics("ev").feature("expl1").set("start", "otl.t_inEclipse+20[min]");
    model.component("comp1").physics("ev").feature("expl1").set("period", "otl.T_eclipse");
    model.component("comp1").physics("ev").feature("expl1").setIndex("reInitName", "HS1", 0, 0);
    model.component("comp1").physics("ev").feature("expl1").setIndex("reInitValue", 0, 0, 0);
    model.component("comp1").physics("ev").feature("expl1").setIndex("reInitValue", 1, 0, 0);
    model.component("comp1").physics("ev").create("expl2", "ExplicitEvent", -1);
    model.component("comp1").physics("ev").feature("expl2").label("\u4eea\u5668 1\uff0c\u5173\u95ed");
    model.component("comp1").physics("ev").feature("expl2").set("start", "otl.t_inEclipse+20[min]+15[min]");
    model.component("comp1").physics("ev").feature("expl2").set("period", "otl.T_eclipse");
    model.component("comp1").physics("ev").feature("expl2").setIndex("reInitName", "HS1", 0, 0);
    model.component("comp1").physics("ev").feature("expl2").setIndex("reInitValue", 0, 0, 0);

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("otl").set("orbitlist", "range(0,0.02,1)");
    model.study("std1").feature("ot").set("orbitlist", "range(0,0.02,4)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset1");
    model.result().dataset("lshl1").selection().geom("geom1", 2);
    model.result().dataset("lshl1").selection().set(5, 12, 39, 83);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").selection().geom("geom1", 3);
    model.result("pg1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").label("\u57df");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result("pg1").feature().create("vol2", "Volume");
    model.result("pg1").feature("vol2").label("\u591a\u5c42\u58f3");
    model.result("pg1").feature("vol2").set("data", "lshl1");
    model.result("pg1").feature("vol2").setIndex("looplevel", 233, 0);
    model.result("pg1").feature("vol2").set("solutionparams", "parent");
    model.result("pg1").feature("vol2").set("titletype", "none");
    model.result("pg1").feature("vol2").set("smooth", "internal");
    model.result("pg1").feature("vol2").set("data", "lshl1");
    model.result("pg1").feature("vol2").set("inheritplot", "vol1");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").label("\u591a\u5c42\u58f3\u8fb9");
    model.result("pg1").feature("line1").set("data", "lshl1");
    model.result("pg1").feature("line1").setIndex("looplevel", 233, 0);
    model.result("pg1").feature("line1").set("solutionparams", "parent");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "fromtheme");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("data", "lshl1");
    model.result().dataset().create("ps1", "ParSurface");
    model.result().dataset("ps1").label("\u884c\u661f");
    model.result().dataset("ps1").set("data", "none");
    model.result().dataset("ps1").set("par1", "Longitude");
    model.result().dataset("ps1").set("parmin1", "-pi");
    model.result().dataset("ps1").set("parmax1", "pi");
    model.result().dataset("ps1").set("par2", "Latitude");
    model.result().dataset("ps1").set("parmin2", "-0.5*pi");
    model.result().dataset("ps1").set("parmax2", "0.5*pi");
    model.result().dataset("ps1")
         .set("exprx", "comp1.otl.x_planet(comp1.otl.R_planet,comp1.otl.rot_planet,comp1.otl.A_off,Latitude,Longitude)");
    model.result().dataset("ps1")
         .set("expry", "comp1.otl.y_planet(comp1.otl.R_planet,comp1.otl.rot_planet,comp1.otl.A_off,Latitude,Longitude)");
    model.result().dataset("ps1")
         .set("exprz", "comp1.otl.z_planet(comp1.otl.R_planet,comp1.otl.rot_planet,Latitude,Longitude)");
    model.result().dataset("ps1").set("global", true);
    model.result().dataset("ps1").set("res1", 360);
    model.result().dataset("ps1").set("res2", 180);
    model.result().dataset("ps1").set("data", "none");
    model.result().dataset().create("tran1", "Transformation3D");
    model.result().dataset("tran1").label("\u822a\u5929\u5668\u8f68\u9053");
    model.result().dataset("tran1").set("data", "none");
    model.result().dataset("tran1").set("transtype", "general");
    model.result().dataset("tran1")
         .set("transmatrix", new String[][]{{"comp1.otl.Tviz11", "comp1.otl.Tviz12", "comp1.otl.Tviz13"}, {"comp1.otl.Tviz21", "comp1.otl.Tviz22", "comp1.otl.Tviz23"}, {"comp1.otl.Tviz31", "comp1.otl.Tviz32", "comp1.otl.Tviz33"}});
    model.result().dataset("tran1")
         .set("translation", new String[]{"comp1.otl.X_viz", "comp1.otl.Y_viz", "comp1.otl.Z_viz"});
    model.result().dataset("tran1").set("data", "none");
    model.result().dataset("ps1").set("data", "dset1");
    model.result().dataset("tran1").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u8f68\u9053\u53ef\u89c6\u5316 (otl)");
    model.result("pg2").set("data", "ps1");
    model.result("pg2").setIndex("looplevel", 233, 0);
    model.result("pg2").feature().create("img1", "Image");
    model.result("pg2").feature("img1").label("\u884c\u661f");
    model.result("pg2").feature("img1").set("showsolutionparams", "on");
    model.result("pg2").feature("img1").set("solutionparams", "parent");
    model.result("pg2").feature("img1").set("filename", "data:///physics/images/earth.jpg");
    model.result("pg2").feature("img1").set("mapping", "manual");
    model.result("pg2").feature("img1").set("uexpr", "0.5*(Longitude-pi)/pi");
    model.result("pg2").feature("img1").set("vexpr", "(Latitude+0.5*pi)/pi");
    model.result("pg2").feature("img1").set("resolution", "norefine");
    model.result("pg2").feature("img1").set("showsolutionparams", "on");
    model.result("pg2").feature("img1").set("data", "parent");
    model.result("pg2").feature().create("pttraj1", "PointTrajectories");
    model.result("pg2").feature("pttraj1").label("\u822a\u5929\u5668\u8f68\u9053");
    model.result("pg2").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg2").feature("pttraj1").set("solutionparams", "parent");
    model.result("pg2").feature("pttraj1").set("expr", new String[]{"otl.X_ECSViz", "otl.Y_ECSViz", "otl.Z_ECSViz"});
    model.result("pg2").feature("pttraj1").set("titletype", "none");
    model.result("pg2").feature("pttraj1").set("linetype", "tube");
    model.result("pg2").feature("pttraj1").set("interpolation", "uniform");
    model.result("pg2").feature("pttraj1").set("interpcount", 500);
    model.result("pg2").feature("pttraj1").set("radiusexpr", "0.01*otl.R_planet");
    model.result("pg2").feature("pttraj1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg2").feature("pttraj1").set("data", "parent");
    model.result("pg2").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg2").feature("pttraj1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88);
    model.result("pg2").feature("pttraj1").feature().create("col1", "Color");
    model.result("pg2").feature("pttraj1").feature("col1").set("expr", "otl.isIlluminated");
    model.result("pg2").feature("pttraj1").feature("col1").set("rangecoloractive", "on");
    model.result("pg2").feature("pttraj1").feature("col1").set("rangecolormax", 1);
    model.result("pg2").feature("pttraj1").feature("col1").set("coloring", "gradient");
    model.result("pg2").feature("pttraj1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("pttraj1").feature("col1").set("topcolor", "yellow");
    model.result("pg2").feature().create("pttraj2", "PointTrajectories");
    model.result("pg2").feature("pttraj2").label("\u592a\u9633\u77e2\u91cf");
    model.result("pg2").feature("pttraj2").set("showsolutionparams", "on");
    model.result("pg2").feature("pttraj2").set("solutionparams", "parent");
    model.result("pg2").feature("pttraj2")
         .set("expr", new String[]{"-1.5*otl.R_planet*otl.SVX_ECS", "-1.5*otl.R_planet*otl.SVY_ECS", "-1.5*otl.R_planet*otl.SVZ_ECS"});
    model.result("pg2").feature("pttraj2").set("titletype", "none");
    model.result("pg2").feature("pttraj2").set("linetype", "tube");
    model.result("pg2").feature("pttraj2").set("radiusexpr", "0.01*otl.R_planet");
    model.result("pg2").feature("pttraj2").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("pttraj2").set("pointtype", "arrow");
    model.result("pg2").feature("pttraj2").set("pointcolor", "yellow");
    model.result("pg2").feature("pttraj2")
         .set("arrowexpr", new String[]{"0.5*otl.SVX_ECS*otl.R_planet", "0.5*otl.SVY_ECS*otl.R_planet", "0.5*otl.SVZ_ECS*otl.R_planet"});
    model.result("pg2").feature("pttraj2").set("arrowbase", "head");
    model.result("pg2").feature("pttraj2").set("arrowscaleactive", true);
    model.result("pg2").feature("pttraj2").set("showsolutionparams", "on");
    model.result("pg2").feature("pttraj2").set("data", "parent");
    model.result("pg2").feature("pttraj2").selection().geom("geom1", 0);
    model.result("pg2").feature("pttraj2").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u65e5/\u6708\u98df");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("titletype", "none");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "black");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg2").feature("surf1").feature("filt1").set("expr", "otl.SVX_ECS*x+otl.SVY_ECS*y+otl.SVZ_ECS*z>0");
    model.result("pg2").feature("surf1").feature("filt1").set("shownodespec", "on");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.7);
    model.result("pg2").feature("surf1").feature().create("def1", "Deform");
    model.result("pg2").feature("surf1").feature("def1")
         .set("expr", new String[]{"(-1+otl.scale_eclipse/otl.R_planet)*x", "(-1+otl.scale_eclipse/otl.R_planet)*y", "(-1+otl.scale_eclipse/otl.R_planet)*z"});
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u8d64\u9053");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("expr", "Latitude");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("levelmethod", "levels");
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "black");
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature().create("con2", "Contour");
    model.result("pg2").feature("con2").label("\u672c\u521d\u5b50\u5348\u7ebf");
    model.result("pg2").feature("con2").set("showsolutionparams", "on");
    model.result("pg2").feature("con2").set("solutionparams", "parent");
    model.result("pg2").feature("con2").set("expr", "Longitude");
    model.result("pg2").feature("con2").set("titletype", "none");
    model.result("pg2").feature("con2").set("levelmethod", "levels");
    model.result("pg2").feature("con2").set("coloring", "uniform");
    model.result("pg2").feature("con2").set("colorlegend", false);
    model.result("pg2").feature("con2").set("color", "black");
    model.result("pg2").feature("con2").set("smooth", "internal");
    model.result("pg2").feature("con2").set("showsolutionparams", "on");
    model.result("pg2").feature("con2").set("data", "parent");
    model.result("pg2").feature().create("surf2", "Surface");
    model.result("pg2").feature("surf2").label("\u822a\u5929\u5668");
    model.result("pg2").feature("surf2").set("data", "tran1");
    model.result("pg2").feature("surf2").setIndex("looplevel", 233, 0);
    model.result("pg2").feature("surf2").set("solutionparams", "parent");
    model.result("pg2").feature("surf2").set("expr", "otl.J");
    model.result("pg2").feature("surf2").set("resolution", "norefine");
    model.result("pg2").feature("surf2").set("smooth", "internal");
    model.result("pg2").feature("surf2").set("data", "tran1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().set(5, 8, 9, 10);
    model.result("pg1").run();
    model.result("pg1").feature().remove("vol2");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("titletype", "none");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg1").feature("surf1").feature("sel1").selection().set(1, 4, 6, 7, 11);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "label");
    model.result("pg3").label("\u968f\u65f6\u95f4\u53d8\u5316\u7684\u6700\u9ad8\u548c\u6700\u4f4e\u6e29\u5ea6");
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").set("legendpos", "middleright");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u6e29\u5ea6 (degC)");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "maxop1(T)", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "degC", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u6700\u9ad8\u6e29\u5ea6", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "minop1(T)", 1);
    model.result("pg3").feature("glob1").setIndex("unit", "degC", 1);
    model.result("pg3").feature("glob1").setIndex("descr", "\u6700\u4f4e\u6e29\u5ea6", 1);
    model.result("pg3").run();
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linewidth", "preference");
    model.result("pg3").feature("glob2").setIndex("expr", "otl.isIlluminated", 0);
    model.result("pg3").feature("glob2").setIndex("descr", "\u53d7\u7167", 0);
    model.result("pg3").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg3").feature("glob2").set("legend", false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u822a\u5929\u5668\u70ed\u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u8ba1\u7b97\u591a\u4e2a\u8f68\u9053\u5468\u671f\u7684\u536b\u661f\u6e29\u5ea6\u7684\u5de5\u4f5c\u6d41\u7a0b\uff1b\u5176\u4e2d\u5728\u201c\u8f68\u9053\u70ed\u8f7d\u8377\u201d\u7814\u7a76\u4e2d\u9884\u5148\u8ba1\u7b97\u592a\u9633\u76f4\u63a5\u8f90\u5c04\u3001\u53cd\u7167\u7387\u548c\u5730\u7403\u7ea2\u5916\u70ed\u8f7d\u8377\uff0c\u5e76\u5728\u201c\u8f68\u9053\u6e29\u5ea6\u201d\u7814\u7a76\u7684\u591a\u4e2a\u8f68\u9053\u5468\u671f\u4e2d\u91cd\u7528\u8fd9\u4e9b\u7ed3\u679c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("spacecraft_thermal_analysis.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
