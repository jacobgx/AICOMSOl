/*
 * eyeglass_frame_fatigue.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:02 by COMSOL 6.3.0.290. */
public class eyeglass_frame_fatigue {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Strain_Life");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "eyeglass_frame_fatigue.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{10, 4});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{55, -8});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("imp1", "r1");
    model.component("comp1").geom("geom1").feature("int1").set("keep", true);
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("r1", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Monel 400 [solid,annealed]");
    model.component("comp1").material("mat1").info().create("UNS");
    model.component("comp1").material("mat1").info("UNS").body("N04400");
    model.component("comp1").material("mat1").info("UNS").title("UNS");
    model.component("comp1").material("mat1").info().create("WNR");
    model.component("comp1").material("mat1").info("WNR").body("2.4360");
    model.component("comp1").material("mat1").info("WNR").title("WNR");
    model.component("comp1").material("mat1").info().create("EN_DIN");
    model.component("comp1").material("mat1").info("EN_DIN").body("NW4400/NiCu30/NiCu30Fe");
    model.component("comp1").material("mat1").info("EN_DIN").title("EN_DIN");
    model.component("comp1").material("mat1").info().create("JIS");
    model.component("comp1").material("mat1").info("JIS").body("NW4400");
    model.component("comp1").material("mat1").info("JIS").title("JIS");
    model.component("comp1").material("mat1").info().create("AFNOR");
    model.component("comp1").material("mat1").info("AFNOR").body("NW4400");
    model.component("comp1").material("mat1").info("AFNOR").title("AFNOR");
    model.component("comp1").material("mat1").info().create("\u7ec4\u5206");
    model.component("comp1").material("mat1").info("\u7ec4\u5206")
         .body("63 Ni+Co min, (28-34) Cu, 2.5 Fe max, 2 Mn max, 0.5 Si max, 0.3 C max (wt %)");
    model.component("comp1").material("mat1").info("\u7ec4\u5206").title("\u7ec4\u5206");
    model.component("comp1").material("mat1").info().create("\u6ce8");
    model.component("comp1").material("mat1").info("\u6ce8")
         .body("Curie temperature = 21 \u00b0C (294 K) to 49 \u00b0C (322 K)");
    model.component("comp1").material("mat1").info("\u6ce8").title("\u6ce8");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", "k_solid_annealed_1(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "\u5f15\u7528: Data Sheet Special Metals Corporation, Huntington, WV; V.J. Johnson, \"A Compendium of The Properties of Materials At Low Temperature (PHASE 1) Part 2. Properties of Solids\", WADD TR 60-56, Part 2, AD0249786 (1960) https://contrails.library.iit.edu/item/158048\n\u6ce8: annealed");
    model.component("comp1").material("mat1").propertyGroup("def").set("resistivity", "res(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("resistivity", "\u5f15\u7528: Data Sheet Special Metals Corporation, Huntington, WV");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", "(alpha(T)+(Tempref-293[K])*if(abs(T-Tempref)>1e-3,(alpha(T)-alpha(Tempref))/(T-Tempref),d(alpha(T),T)))/(1+alpha(Tempref)*(Tempref-293[K]))");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "\u5f15\u7528: A. Goldsmith, H.J. Hirschhorn, and T.E. Waterman, \"Thermophysical properties of solid materials. volume II: Alloys (Melting Temperature above 1000 F)\", WADC Technical Report 58-476, v2, AD253710 (1960) http://contrails.iit.edu/reports/6803; Special Metals Corporation Product Data Sheet SMC-053 (2005)\n\u6ce8: the reference temperature is 20 \u00b0C (293 K), average of values from annealed, hot-rolled, cast and cold-rolled materials, calculated from the linear expansion\n\u53c2\u8003\u6e29\u5ea6: 293.00[K]");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "C(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "\u5f15\u7528: Data Sheet Special Metals Corporation, Huntington, WV");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", "sigma(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "\u5f15\u7528: Data Sheet Special Metals Corporation, Huntington, WV\n\u6ce8: calculated as the reciprocal of the resistivity");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "\u5f15\u7528: A. Goldsmith, H.J. Hirschhorn, and T.E. Waterman, \"Thermophysical properties of solid materials. volume II: Alloys (Melting Temperature above 1000 F)\", WADC Technical Report 58-476, v2, AD253710 (1960) http://contrails.iit.edu/reports/6803; Special Metals Corporation Product Data Sheet SMC-053 (2005)\n\u6ce8: average of values from annealed, hot-rolled, cast and cold-rolled materials, calculated from the linear expansion and the room temperature density");
    model.component("comp1").material("mat1").propertyGroup("def").set("TD", "TD_solid_annealed_1(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("TD", "\u5f15\u7528: Data Sheet Special Metals Corporation, Huntington, WV; Data Sheet Special Metals Corporation, Huntington, WV; V.J. Johnson, \"A Compendium of The Properties of Materials At Low Temperature (PHASE 1) Part 2. Properties of Solids\", WADD TR 60-56, Part 2, AD0249786 (1960) https://contrails.library.iit.edu/item/158048\n\u6ce8: calculated from the thermal conductivity, density, and specific heat");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k_solid_annealed_1", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("k_solid_annealed_1")
         .set("funcname", "k_solid_annealed_1");
    model.component("comp1").material("mat1").propertyGroup("def").func("k_solid_annealed_1").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k_solid_annealed_1")
         .set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("k_solid_annealed_1")
         .set("pieces", new String[][]{{"4.0", "31.0", "-0.841836615+0.566916548*T^1-0.0605683264*T^2+0.00748462102*T^3-4.10323569E-4*T^4+1.03187276E-5*T^5-9.85066671E-8*T^6"}, {"31.0", "147.0", "-0.668360576+0.56426554*T^1-0.00857232135*T^2+7.73363649E-5*T^3-4.01902534E-7*T^4+1.12650186E-9*T^5-1.32470245E-12*T^6"}, {"147.0", "1255.41", "8.64587155+0.126065325*T^1-5.44120506E-4*T^2+1.34580192E-6*T^3-1.64780184E-9*T^4+9.83384922E-13*T^5-2.28302509E-16*T^6"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k_solid_annealed_1").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("k_solid_annealed_1")
         .set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("k_solid_annealed_1").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("res", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("res").set("funcname", "res");
    model.component("comp1").material("mat1").propertyGroup("def").func("res").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("res").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("res")
         .set("pieces", new String[][]{{"77.0", "1366.51", "2.391838E-7+1.5204E-9*T^1-2.652848E-12*T^2+2.145325E-15*T^3-6.108918E-19*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("res").label("Piecewise 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("res").set("fununit", "ohm*m");
    model.component("comp1").material("mat1").propertyGroup("def").func("res").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("alpha", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("funcname", "alpha");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha")
         .set("pieces", new String[][]{{"0.0", "107.0", "8.76079383E-6+2.92792879E-8*T^1+8.48123065E-11*T^2-6.21111671E-13*T^3"}, {"107.0", "177.0", "-3.795047194E-6+3.43583437E-7*T^1-2.68754176E-9*T^2+9.39102813E-12*T^3-1.22011586E-14*T^4"}, {"177.0", "1366.51", "1.206350337E-5+4.89832173E-9*T^1-2.98610907E-13*T^2"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("C", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("C").set("funcname", "C");
    model.component("comp1").material("mat1").propertyGroup("def").func("C").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("C").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("C")
         .set("pieces", new String[][]{{"88.0", "588.71", "-100.9874089+4.836331624*T^1-0.0170401517*T^2+2.743972637E-5*T^3-1.633816436E-8*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("C").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("C").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("C").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("sigma", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("funcname", "sigma");
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma")
         .set("pieces", new String[][]{{"77.0", "1366.51", "1/(-6.108918E-19*T^4+2.145325E-15*T^3-2.652848E-12*T^2+1.520400E-09*T+2.391838E-07)"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("fununit", "S/m");
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("funcname", "rho");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"0.0", "257.0", "8865.194557-0.0478555357*T^1+0.00144207487*T^2-2.56021094E-5*T^3+1.01351547E-7*T^4-1.30099574E-10*T^5"}, {"257.0", "1366.51", "8891.11187-0.284243638*T^1-1.28304726E-4*T^2+1.48689125E-8*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 5");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("TD_solid_annealed_1", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("TD_solid_annealed_1")
         .set("funcname", "TD_solid_annealed_1");
    model.component("comp1").material("mat1").propertyGroup("def").func("TD_solid_annealed_1").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("TD_solid_annealed_1")
         .set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("def").func("TD_solid_annealed_1")
         .set("pieces", new String[][]{{"88.0", "231.0", "3.016957081E-5-5.29371819E-7*T^1+4.99526666E-9*T^2-2.46459928E-11*T^3+6.20932699E-14*T^4-6.29459694E-17*T^5"}, {"231.0", "588.71", "1.11749236E-5-5.48060344E-8*T^1+2.04320228E-10*T^2-3.16920836E-13*T^3+1.86668075E-16*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("TD_solid_annealed_1").label("Piecewise 6");
    model.component("comp1").material("mat1").propertyGroup("def").func("TD_solid_annealed_1")
         .set("fununit", "m^2/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("TD_solid_annealed_1").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("strainreferencetemperature");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ThermalExpansion", "ThermalExpansion", "\u70ed\u81a8\u80c0");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion")
         .set("dL", "(dL(T)-dL(Tempref))/(1+dL(Tempref))");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion")
         .setPropertyInfo("dL", "\u5f15\u7528: A. Goldsmith, H.J. Hirschhorn, and T.E. Waterman, \"Thermophysical properties of solid materials. volume II: Alloys (Melting Temperature above 1000 F)\", WADC Technical Report 58-476, v2, AD253710 (1960) http://contrails.iit.edu/reports/6803; Special Metals Corporation Product Data Sheet SMC-053 (2005)\n\u6ce8: the reference temperature is 20 \u00b0C (293 K), average of values from annealed, hot-rolled, cast and cold-rolled materials\n\u53c2\u8003\u6e29\u5ea6: 293.00[K]");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").set("alphatan", "CTE(T)");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion")
         .setPropertyInfo("alphatan", "\u5f15\u7528: A. Goldsmith, H.J. Hirschhorn, and T.E. Waterman, \"Thermophysical properties of solid materials. volume II: Alloys (Melting Temperature above 1000 F)\", WADC Technical Report 58-476, v2, AD253710 (1960) http://contrails.iit.edu/reports/6803; Special Metals Corporation Product Data Sheet SMC-053 (2005)\n\u6ce8: average of values from annealed, hot-rolled, cast and cold-rolled materials, calculated from the linear expansion");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func().create("dL", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL").set("funcname", "dL");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL")
         .set("pieces", new String[][]{{"0.0", "152.0", "-0.002566075377-1.28314837E-7*T^1+1.69605795E-8*T^2+1.05143788E-10*T^3+3.61264601E-14*T^4"}, {"152.0", "1366.51", "-0.003535086629+1.06299338E-5*T^1+4.98581477E-9*T^2-2.98610934E-13*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL").set("fununit", "");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("dL").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func().create("CTE", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("CTE").set("funcname", "CTE");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("CTE").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("CTE")
         .set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("CTE")
         .set("pieces", new String[][]{{"0.0", "107.0", "3.3921159E-8*T^1+3.15431364E-10*T^2+1.4450584E-13*T^3"}, {"107.0", "177.0", "4.293236581E-4-1.41551639E-5*T^1+1.83065472E-7*T^2-1.14439337E-9*T^3+3.48744061E-12*T^4-4.16533532E-15*T^5"}, {"177.0", "1366.51", "1.076818828E-5+9.97162954E-9*T^1-8.95832802E-13*T^2"}});
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("CTE").label("Piecewise 1");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("CTE").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").func("CTE").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ThermalExpansion")
         .addInput("strainreferencetemperature");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu")
         .setPropertyInfo("E", "\u5f15\u7528: Data Sheet Special Metals Corporation, Huntington, WV");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.32");
    model.component("comp1").material("mat1").propertyGroup("Enu")
         .setPropertyInfo("nu", "\u5f15\u7528: Special Metals Corporation Product Data Sheet SMC-053 (2005)\n\u6ce8: room temperature value");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("E", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E").set("extrap", "constant");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E")
         .set("pieces", new String[][]{{"293.0", "1144.0", "2.092662E11-1.166588E8*T^1+172116.1*T^2-116.639*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E").set("fununit", "Pa");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("E").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup()
         .create("KG", "KG", "\u4f53\u79ef\u6a21\u91cf\u548c\u526a\u5207\u6a21\u91cf");
    model.component("comp1").material("mat1").propertyGroup("KG").set("G", "7.08057955E10[N/m^2]");
    model.component("comp1").material("mat1").propertyGroup("KG")
         .setPropertyInfo("G", "\u5f15\u7528: Data Sheet Special Metals Corporation, Huntington, WV\n\u6ce8: calculated from E and v, errors may be large, room temperature value");
    model.component("comp1").material("mat1").propertyGroup("KG").set("K", "1.73080833E11[N/m^2]");
    model.component("comp1").material("mat1").propertyGroup("KG")
         .setPropertyInfo("K", "\u5f15\u7528: Data Sheet Special Metals Corporation, Huntington, WV\n\u6ce8: calculated from E and v, errors may be large, room temperature value");
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
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"0.32"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("CR-39");
    model.component("comp1").material("mat2").selection().set(4, 5);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"2.1[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.4"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1.3[g/cm^3]"});

    model.component("comp1").physics("solid").prop("d").set("d", 0.001);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(3);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(42);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1").set("force", new int[]{0, 1, 0});

    model.group().create("lg1", "LoadGroup");

    model.component("comp1").physics("solid").feature("bndl1").set("loadGroup", "lg1");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 15);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(22);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 20);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 4", 3);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 3, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 3, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 4", 3);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 3, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 3, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", 0, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", 4, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", -4, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 3, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", 0, 3, 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u4e3b\u5e94\u53d8 (solid)");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("legendpos", "bottom");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("data", "dset1");
    model.result("pg2").feature("surf1").setIndex("looplevel", 2, 0);
    model.result("pg2").feature("surf1").set("expr", "solid.ep1");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v+0.020"});
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").setIndex("looplevel", 3, 0);
    model.result("pg2").feature("surf2").set("expr", "solid.ep3");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("def").set("expr", new String[]{"u", "v-0.020"});
    model.result("pg2").run();

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg", false);

    model.component("comp1").physics("ftg").create("elif1", "StrainLifeModel", 2);
    model.component("comp1").physics("ftg").feature("elif1").selection().set(1, 2, 3);
    model.component("comp1").physics("ftg").feature("elif1").set("ftgElifCrit", "CombinedBAndCM");
    model.component("comp1").physics("ftg").feature("elif1").set("fatigueInputPhysics", "solid");
    model.component("comp1").physics("ftg").feature("elif1").set("Ncut", "5.75e6");

    model.component("comp1").material("mat1").propertyGroup()
         .create("fatigueStressBasquin", "fatigueStressBasquin", "Basquin[Fatigue]");
    model.component("comp1").material("mat1").propertyGroup("fatigueStressBasquin")
         .set("sigmaf_Basquin", new String[]{"970[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("fatigueStressBasquin")
         .set("b_Basquin", new String[]{"-0.077"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("fatigueStrainCoffinManson", "fatigueStrainCoffinManson", "Coffin-Manson[Fatigue]");
    model.component("comp1").material("mat1").propertyGroup("fatigueStrainCoffinManson")
         .set("epsilonf_CM", new String[]{"0.738"});
    model.component("comp1").material("mat1").propertyGroup("fatigueStrainCoffinManson")
         .set("c_CM", new String[]{"-0.54"});

    model.study().create("std2");
    model.study("std2").create("ftge", "Fatigue");
    model.study("std2").feature("ftge").set("ftplistmethod", "manual");
    model.study("std2").feature("ftge").set("solnum", "auto");
    model.study("std2").feature("ftge").set("usesol", "off");

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("ftge").set("outputmap", new String[]{});
    model.study("std2").feature("ftge").setSolveFor("/physics/solid", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("ftge").set("usesol", true);
    model.study("std2").feature("ftge").set("notsolmethod", "sol");
    model.study("std2").feature("ftge").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"ftg.ctf"});
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg3").feature("surf1").set("colortablerev", true);
    model.result("pg3").feature("surf1").set("colortable", "Traffic");
    model.result("pg3").label("\u5931\u6548\u5faa\u73af\u6b21\u6570 (ftg)");
    model.result("pg3").feature("surf1").create("mrkr1", "Marker");
    model.result("pg3").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg3").feature("surf1").feature("mrkr1").set("display", "min");
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("colorlegend", true);
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("colorlegend", true);
    model.result("pg2").run();

    model.title("\u773c\u955c\u67b6\u7684\u75b2\u52b3\u7834\u574f");

    model
         .description("\u4e3a\u4e86\u51cf\u8f7b\u773c\u955c\u67b6\u7684\u91cd\u91cf\uff0c\u9700\u8981\u51cf\u5c0f\u5176\u6a2a\u622a\u9762\u79ef\u3002\u9f3b\u5b50\u4e0a\u65b9\u7684\u8584\u622a\u9762\u4f20\u9012\u7740\u4e24\u4e2a\u534a\u6846\u7684\u6574\u4e2a\u8f7d\u8377\u3002\u672c\u4f8b\u4f7f\u7528 Basquin \u548c Coffin-Manson \u7ec4\u5408\u6a21\u578b\u6765\u9884\u6d4b\u773c\u955c\u53d7\u5230\u5f2f\u66f2\u65f6\u7684\u75b2\u52b3\u5bff\u547d\u3002");

    model.label("eyeglass_frame_fatigue.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
