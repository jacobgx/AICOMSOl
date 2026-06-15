/*
 * surface_resistor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:32 by COMSOL 6.3.0.290. */
public class surface_resistor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Thermal_Stress");

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

    model.param().set("T_air", "300[K]");
    model.param().descr("T_air", "\u7a7a\u6c14\u6e29\u5ea6");
    model.param().set("h_air", "10[W/(m^2*K)]");
    model.param().descr("h_air", "\u4f20\u70ed\u7cfb\u6570");
    model.param().set("Psource", "0.2[W]/2");
    model.param().descr("Psource", "\u4e00\u534a\u51e0\u4f55\u4e0a\u7535\u963b\u5668\u7684\u6563\u70ed\u91cf");
    model.param().set("p0", "1[atm]");
    model.param().descr("p0", "\u7a7a\u6c14\u538b\u529b");
    model.param().set("T0", "80[degC]");
    model.param().descr("T0", "\u521d\u59cb\u6e29\u5ea6\u4f30\u503c");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{2, 0.035});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 0.975, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 0.63, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 0.8, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 0.2, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 0.035, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("x", "0 1.5 1.5 1.5 1.5 0.975 0.975 0.975");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("y", "0.035 0.035 0.035 0.08 0.08 0.08 0.08 0.63");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("pol1", "qb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new double[]{0.525, 0.55});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new double[]{0.975, 0.08});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new double[]{0.52, 0.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("pos", new double[]{1, 0.105});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input")
         .set("csol1", "r1", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("pos", new int[]{4, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new double[]{6, 0.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("pos", new double[]{1, 0.105});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex1").set("dif1", 12);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex2").set("mir1", 12);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol2", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol2").selection("input")
         .set("dif1", "ls1", "mir1", "r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input").set("csol2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pard1").selection("domain")
         .set("uni1", 2, 5, 7);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pard1")
         .set("partitionwith", "extendededges");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pard1").selection("extendededge")
         .set("uni1", 4, 30, 11);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pard1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pard2", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pard2").selection("domain")
         .set("pard1", 4, 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pard2").set("partitionwith", "lines");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pard2").selection("vertexline")
         .set("pard1", 4, 5, 6, 7, 22, 23, 25, 26);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pard2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 1.5, 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{4, 16, 1.6});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{0, -4, -1.6});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("imprint", true);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input").set("fin", 17, 22, 25, 41, 59, 60, 62);
    model.component("comp1").geom("geom1").run("mcf1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("FR4 (Circuit Board)");
    model.component("comp1").material("mat1").set("family", "pcb");
    model.component("comp1").material("mat1").set("color", "custom");
    model.component("comp1").material("mat1").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "22[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.15");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Alumina");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"8e-6[1/K]", "0", "0", "0", "8e-6[1/K]", "0", "0", "0", "8e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "3900[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"27[W/(m*K)]", "0", "0", "0", "27[W/(m*K)]", "0", "0", "0", "27[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "300[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.222");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat3").label("Copper");
    model.component("comp1").material("mat3").set("family", "copper");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat3").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("Silver [solid,annealed]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", "k_solid_annealed_1(T)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "\u5f15\u7528: C.Y. Ho, R.W. Powell, and P.E. Liley, \"Thermal Conductivity of the Elements\", Journal of Physical and Chemical Reference Data, v1, No. 2, p279 (1972) https://srd.nist.gov/JPCRD/jpcrd7.pdf\n\u6ce8: T\u0098\u009a = 962 \u00b0C (1235 K), well-annealed with residual resistivity of 6.21 x 10{\u00b9p ohm-cm, error is 2% near RT, 5% at others");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", "(alpha_solid_1(T)+(Tempref-293[K])*if(abs(T-Tempref)>1e-3,(alpha_solid_1(T)-alpha_solid_1(Tempref))/(T-Tempref),d(alpha_solid_1(T),T)))/(1+alpha_solid_1(Tempref)*(Tempref-293[K]))");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "\u5f15\u7528: F.C. Nix and D. MacNair, \"The Thermal Expansion of Pure Metals. II: Molybdenum, Palladium, Silver, Tantalum, Tungsten, Platinum, and Lead\", Physical Review, v61, p74 (1942) https://doi.org/10.1103/PhysRev.61.74; E.A. Owen and E.L. Yates, \"IX. The thermal expansion of the crystal lattices of silver, platinum, and zinc\", Philosophical Magazine, Series 7, v17, No. 110, p113 (1934) https://doi.org/10.1080/14786443409462374; N. Waterhouse and B. Yates, \"The interferometric measurement of the thermal expansion of silver and palladium at low temperatures\", Cryogenics, v8, p267 (1968) https://doi.org/10.1016/S0011-2275(68)80001-3; R.J. Corruccini and J.J. Gniewek, \"Thermal Expansion of Technical Solids at Low Temperatures: A Compilation From the Literature\", NBS Monograph 29 (1961) https://digital.library.unt.edu/ark:/67531/metadc70437/\n\u6ce8: the reference temperature is 20 \u00b0C (293 K), T\u0098\u009a = 962 \u00b0C (1235 K), values below -203.1 \u00b0C (70 K) were calculated from the Gruneisen correlation\n\u53c2\u8003\u6e29\u5ea6: 293.00[K]");
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "C_solid_1(T)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "\u5f15\u7528: B.J. McBride, S. Gordon, and M.A. Reno, \"Thermodynamic Data for Fifty Reference Elements\", NASA Technical Paper 3287 (1993) https://ntrs.nasa.gov/archive/nasa/casi.ntrs.nasa.gov/20010021116.pdf; D.R. Smith and F.R. Fickett, \"Low-Temperature Properties of Silver\", Journal of Research National Institute Standards Technology, v100, No. 2, p119 (1995) https://doi.org/10.6028/jres.100.012\n\u6ce8: T\u0098\u009a = 962 \u00b0C (1235 K)");
    model.component("comp1").material("mat4").propertyGroup("def").set("HC", "HC_solid_1(T)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("HC", "\u5f15\u7528: B.J. McBride, S. Gordon, and M.A. Reno, \"Thermodynamic Data for Fifty Reference Elements\", NASA Technical Paper 3287 (1993) https://ntrs.nasa.gov/archive/nasa/casi.ntrs.nasa.gov/20010021116.pdf; D.R. Smith and F.R. Fickett, \"Low-Temperature Properties of Silver\", Journal of Research National Institute Standards Technology, v100, No. 2, p119 (1995) https://doi.org/10.6028/jres.100.012\n\u6ce8: T\u0098\u009a = 962 \u00b0C (1235 K)");
    model.component("comp1").material("mat4").propertyGroup("def").set("VP", "VP_solid_1(T)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("VP", "\u5f15\u7528: C.B. Alcock, V.P. Itkin, and M.K. Horrigan, \"Vapour Pressure Equations for the Metallic Elements: 298-2500K, Canadian Metallurgical Quarterly\", v23, No. 3, p309 (1984) https://doi.org/10.1179/cmq.1984.23.3.309\n\u6ce8: T\u0098\u009a = 962 \u00b0C (1235 K), 5% error or less");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "rho_solid_1(T)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("density", "\u5f15\u7528: F.C. Nix and D. MacNair, \"The Thermal Expansion of Pure Metals. II: Molybdenum, Palladium, Silver, Tantalum, Tungsten, Platinum, and Lead\", Physical Review, v61, p74 (1942) https://doi.org/10.1103/PhysRev.61.74; E.A. Owen and E.L. Yates, \"IX. The thermal expansion of the crystal lattices of silver, platinum, and zinc\", Philosophical Magazine, Series 7, v17, No. 110, p113 (1934) https://doi.org/10.1080/14786443409462374; N. Waterhouse and B. Yates, \"The interferometric measurement of the thermal expansion of silver and palladium at low temperatures\", Cryogenics, v8, p267 (1968) https://doi.org/10.1016/S0011-2275(68)80001-3; R.J. Corruccini and J.J. Gniewek, \"Thermal Expansion of Technical Solids at Low Temperatures: A Compilation From the Literature\", NBS Monograph 29 (1961) https://digital.library.unt.edu/ark:/67531/metadc70437/\n\u6ce8: T\u0098\u009a = 962 \u00b0C (1235 K), calculated from the linear expansion and the room temperature density");
    model.component("comp1").material("mat4").propertyGroup("def").set("S", "S(T)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("S", "\u5f15\u7528: N. Cusack and P. Kendall, \"The Absolute Scale of Thermoelectric Power at High Temperature\", Proceedings of the Physical Society, v72, No. 5 (1958) https://doi.org/10.1088/0370-1328/72/5/429\n\u6ce8: T\u0098\u009a = 962 \u00b0C (1235 K), p-type");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("k_solid_annealed_1", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("def").func("k_solid_annealed_1")
         .set("funcname", "k_solid_annealed_1");
    model.component("comp1").material("mat4").propertyGroup("def").func("k_solid_annealed_1").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("def").func("k_solid_annealed_1")
         .set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("def").func("k_solid_annealed_1")
         .set("pieces", new String[][]{{"0.0", "12.31763", "3984.15514*T^1-79.6943362*T^2+55.1775003*T^3-20.1076316*T^4+1.83852143*T^5-0.0530182898*T^6"}, 
         {"12.31763", "35.0", "11685.1851+5789.52713*T^1-976.813559*T^2+61.6763958*T^3-1.94183418*T^4+0.0306222302*T^5-1.93171328E-4*T^6"}, 
         {"35.0", "100.0", "11741.2623-687.054377*T^1+17.8523078*T^2-0.252172418*T^3+0.00203489646*T^4-8.86687517E-6*T^5+1.62424109E-8*T^6"}, 
         {"100.0", "172.9993", "647.744954-3.6342373*T^1+0.0203715*T^2-3.82464365E-5*T^3"}, 
         {"172.9993", "300.0", "370.275575+1.14373432*T^1-0.00774648962*T^2+2.22554027E-5*T^3-2.32630945E-8*T^4"}, 
         {"300.0", "1235.0", "403.534347+0.236992453*T^1-7.07574291E-4*T^2+7.57664035E-7*T^3-3.37202514E-10*T^4-1.06327174E-14*T^5+3.63030887E-17*T^6"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("k_solid_annealed_1").label("Piecewise");
    model.component("comp1").material("mat4").propertyGroup("def").func("k_solid_annealed_1")
         .set("fununit", "W/(m*K)");
    model.component("comp1").material("mat4").propertyGroup("def").func("k_solid_annealed_1").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("alpha_solid_1", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("def").func("alpha_solid_1")
         .set("funcname", "alpha_solid_1");
    model.component("comp1").material("mat4").propertyGroup("def").func("alpha_solid_1").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("def").func("alpha_solid_1").set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("def").func("alpha_solid_1")
         .set("pieces", new String[][]{{"0.0", "92.0", "1.397008E-5+6.293815E-8*T^1-3.772802E-10*T^2+1.128415E-12*T^3-1.223488E-15*T^4"}, {"92.0", "873.16", "1.604758434E-5+1.576798E-8*T^1-1.719191E-11*T^2+6.931419E-15*T^3"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("alpha_solid_1").label("Piecewise 1");
    model.component("comp1").material("mat4").propertyGroup("def").func("alpha_solid_1").set("fununit", "1/K");
    model.component("comp1").material("mat4").propertyGroup("def").func("alpha_solid_1").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("C_solid_1", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("def").func("C_solid_1").set("funcname", "C_solid_1");
    model.component("comp1").material("mat4").propertyGroup("def").func("C_solid_1").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("def").func("C_solid_1").set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("def").func("C_solid_1")
         .set("pieces", new String[][]{{"1.0", "12.3", "0.01224227103-0.01394369045*T^1+0.01009593342*T^2-4.957659256E-4*T^3+1.661680202E-4*T^4-3.512349174E-6*T^5"}, 
         {"12.3", "75.0", "24.25265406-4.669812608*T^1+0.2956443688*T^2-0.0048535446*T^3+3.380225149E-5*T^4-8.439015032E-8*T^5"}, 
         {"75.0", "300.0", "-63.77215826+5.177264864*T^1-0.03961477726*T^2+1.570453522E-4*T^3-3.10537526E-7*T^4+2.411435011E-10*T^5"}, 
         {"300.0", "1235.0", "225.7805621+0.01705702158*T^1+5.007143424E-5*T^2-1.768497722E-8*T^3"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("C_solid_1").label("Piecewise 2");
    model.component("comp1").material("mat4").propertyGroup("def").func("C_solid_1").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat4").propertyGroup("def").func("C_solid_1").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("HC_solid_1", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("def").func("HC_solid_1").set("funcname", "HC_solid_1");
    model.component("comp1").material("mat4").propertyGroup("def").func("HC_solid_1").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("def").func("HC_solid_1").set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("def").func("HC_solid_1")
         .set("pieces", new String[][]{{"1.0", "12.3", "0.001320549478-0.001504078127*T^1+0.001089027838*T^2-5.347725208E-5*T^3+1.792421416E-5*T^4-3.788700701E-7*T^5"}, 
         {"12.3", "75.0", "2.616086715-0.5037234752*T^1+0.03189056934*T^2-5.235422464E-4*T^3+3.646181527E-6*T^4-9.102999096E-9*T^5"}, 
         {"75.0", "300.0", "-6.879037029+0.558461492*T^1-0.004273165224*T^2+1.694016665E-5*T^3-3.349706216E-8*T^4+2.601166859E-11*T^5"}, 
         {"300.0", "1235.0", "24.35462597+0.001839906887*T^1+5.40110468E-6*T^2-1.907643337E-9*T^3"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("HC_solid_1").label("Piecewise 3");
    model.component("comp1").material("mat4").propertyGroup("def").func("HC_solid_1").set("fununit", "J/(mol*K)");
    model.component("comp1").material("mat4").propertyGroup("def").func("HC_solid_1").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("VP_solid_1", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("def").func("VP_solid_1").set("funcname", "VP_solid_1");
    model.component("comp1").material("mat4").propertyGroup("def").func("VP_solid_1").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("def").func("VP_solid_1").set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("def").func("VP_solid_1")
         .set("pieces", new String[][]{{"293.0", "1235.0", "(exp((-1.499900000e+04/T-7.845000000e-01*log10(T)+1.200781000e+01)*log(10.0)))*1.333200000e+02"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("VP_solid_1").label("Piecewise 4");
    model.component("comp1").material("mat4").propertyGroup("def").func("VP_solid_1").set("fununit", "Pa");
    model.component("comp1").material("mat4").propertyGroup("def").func("VP_solid_1").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("rho_solid_1", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("def").func("rho_solid_1")
         .set("funcname", "rho_solid_1");
    model.component("comp1").material("mat4").propertyGroup("def").func("rho_solid_1").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("def").func("rho_solid_1").set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("def").func("rho_solid_1")
         .set("pieces", new String[][]{{"0.0", "30.0", "10631.18474"}, {"30.0", "140.0", "10631.05965+0.1394367*T^1-0.0048655*T^2+1.188653E-5*T^3"}, {"140.0", "873.16", "10658.96-0.4692536*T^1-2.976784E-4*T^2+1.470941E-7*T^3"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("rho_solid_1").label("Piecewise 5");
    model.component("comp1").material("mat4").propertyGroup("def").func("rho_solid_1").set("fununit", "kg/m^3");
    model.component("comp1").material("mat4").propertyGroup("def").func("rho_solid_1").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("S", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("def").func("S").set("funcname", "S");
    model.component("comp1").material("mat4").propertyGroup("def").func("S").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("def").func("S").set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("def").func("S")
         .set("pieces", new String[][]{{"100.0", "300.0", "1.07321492E-6-9.71209224E-9*T^1+8.17094027E-11*T^2-2.09721393E-13*T^3+2.04700313E-16*T^4"}, {"300.0", "1200.0", "1.6103804E-6-6.26423854E-9*T^1+2.37977855E-11*T^2-1.44560995E-14*T^3+3.26340326E-18*T^4"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("S").label("Piecewise 6");
    model.component("comp1").material("mat4").propertyGroup("def").func("S").set("fununit", "V/K");
    model.component("comp1").material("mat4").propertyGroup("def").func("S").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("strainreferencetemperature");
    model.component("comp1").material("mat4").propertyGroup()
         .create("ThermalExpansion", "ThermalExpansion", "\u70ed\u81a8\u80c0");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion")
         .set("dL", "(dL_solid_1(T)-dL_solid_1(Tempref))/(1+dL_solid_1(Tempref))");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion")
         .setPropertyInfo("dL", "\u5f15\u7528: F.C. Nix and D. MacNair, \"The Thermal Expansion of Pure Metals. II: Molybdenum, Palladium, Silver, Tantalum, Tungsten, Platinum, and Lead\", Physical Review, v61, p74 (1942) https://doi.org/10.1103/PhysRev.61.74; E.A. Owen and E.L. Yates, \"IX. The thermal expansion of the crystal lattices of silver, platinum, and zinc\", Philosophical Magazine, Series 7, v17, No. 110, p113 (1934) https://doi.org/10.1080/14786443409462374; N. Waterhouse and B. Yates, \"The interferometric measurement of the thermal expansion of silver and palladium at low temperatures\", Cryogenics, v8, p267 (1968) https://doi.org/10.1016/S0011-2275(68)80001-3; R.J. Corruccini and J.J. Gniewek, \"Thermal Expansion of Technical Solids at Low Temperatures: A Compilation From the Literature\", NBS Monograph 29 (1961) https://digital.library.unt.edu/ark:/67531/metadc70437/\n\u6ce8: the reference temperature is 20 \u00b0C (293 K), T\u0098\u009a = 962 \u00b0C (1235 K), values below -203.1 \u00b0C (70 K) were calculated from the Gruneisen correlation\n\u53c2\u8003\u6e29\u5ea6: 293.00[K]");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").set("alphatan", "CTE_solid_1(T)");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion")
         .setPropertyInfo("alphatan", "\u5f15\u7528: N. Waterhouse and B. Yates, \"The interferometric measurement of the thermal expansion of silver and palladium at low temperatures\", Cryogenics, v8, p267 (1968) https://doi.org/10.1016/S0011-2275(68)80001-3\n\u6ce8: T\u0098\u009a = 962 \u00b0C (1235 K)");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func()
         .create("dL_solid_1", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("dL_solid_1")
         .set("funcname", "dL_solid_1");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("dL_solid_1").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("dL_solid_1")
         .set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("dL_solid_1")
         .set("pieces", new String[][]{{"0.0", "10.0", "-0.004134560345"}, 
         {"10.0", "30.0", "-0.004151388955+2.566303E-6*T^1-1.154799E-7*T^2+2.71357E-9*T^3"}, 
         {"30.0", "87.0", "-0.004065567005-6.856586E-6*T^1+2.120401E-7*T^2-9.814541E-10*T^3+2.367498E-12*T^4-2.275841E-15*T^5"}, 
         {"87.0", "873.16", "-0.004745212758+1.182195E-5*T^1+1.97186E-8*T^2-1.837579E-11*T^3+6.889976E-15*T^4"}});
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("dL_solid_1")
         .label("Piecewise");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("dL_solid_1")
         .set("fununit", "");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("dL_solid_1")
         .set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func()
         .create("CTE_solid_1", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("CTE_solid_1")
         .set("funcname", "CTE_solid_1");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("CTE_solid_1").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("CTE_solid_1")
         .set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("CTE_solid_1")
         .set("pieces", new String[][]{{"30.0", "300.0", "-8.786433E-6+5.111237E-7*T^1-4.275991E-9*T^2+1.8602E-11*T^3-4.019836E-14*T^4+3.403595E-17*T^5"}});
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("CTE_solid_1")
         .label("Piecewise 1");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("CTE_solid_1")
         .set("fununit", "1/K");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").func("CTE_solid_1")
         .set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("ThermalExpansion")
         .addInput("strainreferencetemperature");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat4").propertyGroup("Enu")
         .setPropertyInfo("E", "\u5f15\u7528: W. Koester, \"Die Temperaturabhangigkeit des blastizitatsmoduls reiner Metalle\", Zeitschrift fuer Metallkunde, v39, No. 1, p1 (1948) https://doi.org/10.1515/ijmr-1948-390101; A. Wolfenden and M.R. Harmouche, \"Elastic constants of silver as a function of temperature\", Journal of Materials Science, v28, No. 4, p1015 (1993) https://doi.org/10.1007/BF00400888; D.R. Smith and F.R. Fickett, \"Low-Temperature Properties of Silver\", Journal of Research National Institute Standards Technology, v100, No. 2, p119 (1995) https://doi.org/10.6028/jres.100.012\n\u6ce8: T\u0098\u009a = 962 \u00b0C (1235 K), 2% error, data above 27 \u00b0C (300 K) were average of Voigt and Reuss bounds");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat4").propertyGroup("Enu")
         .setPropertyInfo("nu", "\u5f15\u7528: A. Wolfenden and M.R. Harmouche, \"Elastic constants of silver as a function of temperature\", Journal of Materials Science, v28, No. 4, p1015 (1993) https://doi.org/10.1007/BF00400888; D.R. Smith and F.R. Fickett, \"Low-Temperature Properties of Silver\", Journal of Research National Institute Standards Technology, v100, No. 2, p119 (1995) https://doi.org/10.6028/jres.100.012\n\u6ce8: T\u0098\u009a = 962 \u00b0C (1235 K), average of Voigt and Reuss bounds");
    model.component("comp1").material("mat4").propertyGroup("Enu").func().create("E", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("Enu").func("E").set("funcname", "E");
    model.component("comp1").material("mat4").propertyGroup("Enu").func("E").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("Enu").func("E").set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("Enu").func("E")
         .set("pieces", new String[][]{{"0.0", "1173.16", "9.143965E10-2.775728E7*T^1-38123.31*T^2+49.99535*T^3-0.02009828*T^4"}});
    model.component("comp1").material("mat4").propertyGroup("Enu").func("E").label("Piecewise");
    model.component("comp1").material("mat4").propertyGroup("Enu").func("E").set("fununit", "Pa");
    model.component("comp1").material("mat4").propertyGroup("Enu").func("E").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("Enu").func().create("nu", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("Enu").func("nu").set("funcname", "nu");
    model.component("comp1").material("mat4").propertyGroup("Enu").func("nu").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("Enu").func("nu").set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("Enu").func("nu")
         .set("pieces", new String[][]{{"0.0", "1173.16", "0.360203+1.499369E-5*T^1+4.008534E-8*T^2-1.475466E-11*T^3-1.181682E-15*T^4"}});
    model.component("comp1").material("mat4").propertyGroup("Enu").func("nu").label("Piecewise 1");
    model.component("comp1").material("mat4").propertyGroup("Enu").func("nu").set("fununit", "");
    model.component("comp1").material("mat4").propertyGroup("Enu").func("nu").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup()
         .create("KG", "KG", "\u4f53\u79ef\u6a21\u91cf\u548c\u526a\u5207\u6a21\u91cf");
    model.component("comp1").material("mat4").propertyGroup("KG").set("G", "mu(T)");
    model.component("comp1").material("mat4").propertyGroup("KG")
         .setPropertyInfo("G", "\u5f15\u7528: A. Wolfenden and M.R. Harmouche, \"Elastic constants of silver as a function of temperature\", Journal of Materials Science, v28, No. 4, p1015 (1993) https://doi.org/10.1007/BF00400888; D.R. Smith and F.R. Fickett, \"Low-Temperature Properties of Silver\", Journal of Research National Institute Standards Technology, v100, No. 2, p119 (1995) https://doi.org/10.6028/jres.100.012\n\u6ce8: T\u0098\u009a = 962 \u00b0C (1235 K), 2% error, average of Voigt and Reuss bounds");
    model.component("comp1").material("mat4").propertyGroup("KG").set("K", "kappa(T)");
    model.component("comp1").material("mat4").propertyGroup("KG")
         .setPropertyInfo("K", "\u5f15\u7528: A. Wolfenden and M.R. Harmouche, \"Elastic constants of silver as a function of temperature\", Journal of Materials Science, v28, No. 4, p1015 (1993) https://doi.org/10.1007/BF00400888; D.R. Smith and F.R. Fickett, \"Low-Temperature Properties of Silver\", Journal of Research National Institute Standards Technology, v100, No. 2, p119 (1995) https://doi.org/10.6028/jres.100.012\n\u6ce8: T\u0098\u009a = 962 \u00b0C (1235 K), average of Voigt and Reuss bounds");
    model.component("comp1").material("mat4").propertyGroup("KG").func().create("mu", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("KG").func("mu").set("funcname", "mu");
    model.component("comp1").material("mat4").propertyGroup("KG").func("mu").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("KG").func("mu").set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("KG").func("mu")
         .set("pieces", new String[][]{{"0.0", "1173.16", "3.362582E10-6732560.0*T^1-31364.68*T^2+40.77008*T^3-0.01638472*T^4"}});
    model.component("comp1").material("mat4").propertyGroup("KG").func("mu").label("Piecewise");
    model.component("comp1").material("mat4").propertyGroup("KG").func("mu").set("fununit", "Pa");
    model.component("comp1").material("mat4").propertyGroup("KG").func("mu").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("KG").func().create("kappa", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("KG").func("kappa").set("funcname", "kappa");
    model.component("comp1").material("mat4").propertyGroup("KG").func("kappa").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("KG").func("kappa").set("extrap", "constant");
    model.component("comp1").material("mat4").propertyGroup("KG").func("kappa")
         .set("pieces", new String[][]{{"0.0", "1173.16", "1.088261E11-3077357.0*T^1-87636.32*T^2+131.2545*T^3-0.05742809*T^4"}});
    model.component("comp1").material("mat4").propertyGroup("KG").func("kappa").label("Piecewise 1");
    model.component("comp1").material("mat4").propertyGroup("KG").func("kappa").set("fununit", "Pa");
    model.component("comp1").material("mat4").propertyGroup("KG").func("kappa").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("KG").addInput("temperature");
    model.component("comp1").material("mat4").set("family", "custom");
    model.component("comp1").material("mat4").set("lighting", "cooktorrance");
    model.component("comp1").material("mat4").set("specular", "custom");
    model.component("comp1").material("mat4").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat4").set("fresnel", 0.9);
    model.component("comp1").material("mat4").set("roughness", 0.1);
    model.component("comp1").material("mat4").set("metallic", 0);
    model.component("comp1").material("mat4").set("pearl", 0);
    model.component("comp1").material("mat4").set("diffusewrap", 0);
    model.component("comp1").material("mat4").set("clearcoat", 0);
    model.component("comp1").material("mat4").set("reflectance", 0);
    model.component("comp1").material("mat4").set("shininess", 130);
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat5").propertyGroup().create("Anand", "Anand", "Anand viscoplasticity");
    model.component("comp1").material("mat5").label("Solder, 60Sn-40Pb");
    model.component("comp1").material("mat5").set("family", "custom");
    model.component("comp1").material("mat5").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat5").set("diffuse", "custom");
    model.component("comp1").material("mat5")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat5").set("ambient", "custom");
    model.component("comp1").material("mat5")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat5").set("noise", true);
    model.component("comp1").material("mat5").set("fresnel", 0.9);
    model.component("comp1").material("mat5").set("roughness", 0.1);
    model.component("comp1").material("mat5").set("diffusewrap", 0);
    model.component("comp1").material("mat5").set("reflectance", 0);
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"6.67e6[S/m]", "0", "0", "0", "6.67e6[S/m]", "0", "0", "0", "6.67e6[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"21e-6[1/K]", "0", "0", "0", "21e-6[1/K]", "0", "0", "0", "21e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "150[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "9000[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"50[W/(m*K)]", "0", "0", "0", "50[W/(m*K)]", "0", "0", "0", "50[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "10[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.4");
    model.component("comp1").material("mat5").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat5").propertyGroup("linzRes").set("rho0", "4.99e-7[ohm*m]");
    model.component("comp1").material("mat5").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("Anand").label("Anand viscoplasticity");
    model.component("comp1").material("mat5").propertyGroup("Anand").set("A_ana", "1.49e7[1/s]");
    model.component("comp1").material("mat5").propertyGroup("Anand").set("Q_ana", "90046[J/mol]");
    model.component("comp1").material("mat5").propertyGroup("Anand").set("xi_ana", "11");
    model.component("comp1").material("mat5").propertyGroup("Anand").set("m_ana", "0.303");
    model.component("comp1").material("mat5").propertyGroup("Anand").set("ssat_ana", "80.42[MPa]");
    model.component("comp1").material("mat5").propertyGroup("Anand").set("sa_init", "56.33[MPa]");
    model.component("comp1").material("mat5").propertyGroup("Anand").set("h0_ana", "2640.75[MPa]");
    model.component("comp1").material("mat5").propertyGroup("Anand").set("a_ana", "1.34");
    model.component("comp1").material("mat5").propertyGroup("Anand").set("n_ana", "0.0231");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat6").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat6").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat6").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat6").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat6").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat6").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat6").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat6").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat6").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat6").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat6").label("Air");
    model.component("comp1").material("mat6").set("family", "air");
    model.component("comp1").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat6").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat6").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat6").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat6").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat6").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat6").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat6").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat6").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat6").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat6").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat6").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat6").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat6").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat6").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat6").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat6").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat6").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat6").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat6").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat6").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat6").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat6").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat6").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat6").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat6").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat6").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat6").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat6").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat6").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat6").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat6").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat6").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat6").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat6").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat6").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat6").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat6").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat6").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat6").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat6").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat6").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat6").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat6").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat6").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat6").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat6").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat6").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat6").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat6").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat6").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat6").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat6").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat6").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat6").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat6").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat6").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat6").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat6").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat6").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat6").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat6").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat6").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat6").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat6").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat6").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat6").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat6").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat6").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat6").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat6").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat6").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat6").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat6").materialType("nonSolid");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat2").selection().set(5);
    model.component("comp1").material("mat3").selection().set(2, 7);
    model.component("comp1").material("mat4").selection().set(4, 9);
    model.component("comp1").material("mat5").selection().set(3, 8);
    model.component("comp1").material("mat6").selection().set(6);

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u5bf9\u79f0\u9762");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("xmax", 0.1);
    model.component("comp1").selection("box1").set("condition", "inside");

    model.component("comp1").physics("solid").selection().set(1, 2, 3, 4, 5, 7, 8, 9);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("box1");
    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().set(8);
    model.component("comp1").physics("solid").create("sym2", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym2").selection().set(2);
    model.component("comp1").physics("solid").feature("sym2").set("NormalDirectionTranslation", "FreeDisplacement");
    model.component("comp1").physics("solid").create("sym3", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym3").selection().set(9);
    model.component("comp1").physics("solid").feature("sym3").set("NormalDirectionTranslation", "FreeDisplacement");
    model.component("comp1").physics("solid").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("solid").feature("disp1").selection().set(1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("ht").create("fluid1", "FluidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("fluid1").selection().set(6);
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().set(5);
    model.component("comp1").physics("ht").feature("hs1").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs1").set("P0", "Psource");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection()
         .set(3, 4, 11, 15, 19, 29, 30, 44, 46, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "h_air");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_air");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(1, 10, 16, 20, 24, 33, 37, 40, 59, 61, 62, 63, 66, 67);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection()
         .set(10, 16, 20, 24, 33, 37, 40, 59, 61, 62, 63, 66, 67);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(13, 64);
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", 0.2);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
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

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "T");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg3").feature().create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("solutionparams", "parent");
    model.result("pg3").feature("iso1").set("expr", "T");
    model.result("pg3").feature("iso1").set("number", 10);
    model.result("pg3").feature("iso1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("iso1").set("smooth", "internal");
    model.result("pg3").feature("iso1").set("showsolutionparams", "on");
    model.result("pg3").feature("iso1").set("data", "parent");
    model.result("pg3").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u710a\u70b9\u4e2d\u7684\u5e94\u529b");
    model.result("pg4").set("edges", false);
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "solid.misesGp");
    model.result("pg4").feature("vol1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg4").feature("vol1").set("colortable", "Prism");
    model.result("pg4").feature("vol1").create("sel1", "Selection");
    model.result("pg4").feature("vol1").feature("sel1").selection().set(8);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg2").run();

    model.title("\u8868\u9762\u8d34\u88c5\u7535\u963b\u7684\u70ed\u673a\u68b0\u5206\u6790");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u4f20\u70ed\u201d\u548c\u201c\u56fa\u4f53\u529b\u5b66\u201d\u63a5\u53e3\u5728\u7a33\u6001\u4e0b\u5206\u6790\u70ed\u81f4\u5e94\u529b\u3002\n\n\u9700\u8981\u201c\u4f20\u70ed\u6a21\u5757\u201d\u548c\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u6216\u201cMEMS \u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("surface_resistor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
