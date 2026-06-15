/*
 * headphone_artificial_ear.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class headphone_artificial_ear {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().label("\u6a21\u578b\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("fmax", "20.0[kHz]", "\u6700\u5927\u9891\u7387");
    model.param().set("cair", "343[m/s]", "\u7a7a\u6c14\u4e2d\u7684\u58f0\u901f");
    model.param().set("cporo", "240[m/s]", "\u591a\u5b54\u57df\u4e2d\u526a\u5207\u6ce2\u7684\u58f0\u901f");
    model.param().set("lambda_air", "cair/fmax", "fmax \u7684\u89e3\u6790\u6ce2\u957f - \u7a7a\u6c14");
    model.param().set("lambda_poro", "cporo/fmax", "fmax \u7684\u89e3\u6790\u6ce2\u957f - \u591a\u5b54\u4ecb\u8d28");
    model.param().create("par2");
    model.param("par2").label("\u7a7f\u5b54\u677f\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("Radp1", "10 [mm]", "\u7a7f\u5b54\u677f 1 \u7684\u534a\u5f84");
    model.param("par2").set("n1", "1", "\u7528\u4e8e\u5b9a\u4e49\u677f 1 \u7684\u5706\u7684\u6570\u91cf");
    model.param("par2").set("dh1", "0.5 [mm]", "\u677f 1 \u4e2d\u7684\u5b54\u5f84");
    model.param("par2").set("tp1", "0.5 [mm]", "\u7a7f\u5b54\u677f 1 \u7684\u539a\u5ea6");
    model.param("par2").set("Nh1", "150", "\u7a7f\u5b54\u677f 1 \u4e2d\u7684\u5b54\u6570");
    model.param("par2")
         .set("sigma1", "(Nh1*pi*dh1^2)/(n1*pi*Radp1^2)", "\u7a7f\u5b54\u677f 1 \u7684\u5b54\u9699\u7387");
    model.param("par2").set("Radp2", "6 [mm]", "\u7a7f\u5b54\u677f 2 \u7684\u534a\u5f84");
    model.param("par2").set("n2", "4", "\u7528\u4e8e\u5b9a\u4e49\u677f 2 \u7684\u5706\u7684\u6570\u91cf");
    model.param("par2").set("dh2", "0.5 [mm]", "\u677f 2 \u4e2d\u7684\u5b54\u5f84");
    model.param("par2").set("tp2", "0.5 [mm]", "\u7a7f\u5b54\u677f 2 \u7684\u539a\u5ea6");
    model.param("par2").set("Nh2", "200", "\u7a7f\u5b54\u677f 2 \u4e2d\u7684\u5b54\u6570");
    model.param("par2")
         .set("sigma2", "(Nh2*pi*dh2^2)/(n2*pi*Radp2^2)", "\u7a7f\u5b54\u677f 2 \u7684\u5b54\u9699\u7387");
    model.param("par2").set("Radp3", "6 [mm]", "\u7a7f\u5b54\u677f 3 \u7684\u534a\u5f84");
    model.param("par2").set("n3", "4", "\u7528\u4e8e\u5b9a\u4e49\u677f 3 \u7684\u5706\u7684\u6570\u91cf");
    model.param("par2").set("dh3", "0.5 [mm]", "\u677f 3 \u4e2d\u7684\u5b54\u5f84");
    model.param("par2").set("tp3", "0.5 [mm]", "\u7a7f\u5b54\u677f 3 \u7684\u539a\u5ea6");
    model.param("par2").set("Nh3", "300", "\u7a7f\u5b54\u677f 3 \u4e2d\u7684\u5b54\u6570");
    model.param("par2")
         .set("sigma3", "(Nh3*pi*dh3^2)/(n3*pi*Radp3^2)", "\u7a7f\u5b54\u677f 3 \u7684\u5b54\u9699\u7387");
    model.param().create("par3");
    model.param("par3").label("Thiele-Small \u53c2\u6570");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("R_g", "0.8[ohm]", "\u5bfc\u7ebf\u7535\u963b");
    model.param("par3").set("n_e", ".7", "\u97f3\u5708\u635f\u8017\u56e0\u5b50");
    model.param("par3").set("R_E", "124.3[ohm]", "\u97f3\u5708\u7535\u963b");
    model.param("par3").set("L_e", "5.53[mH]", "\u97f3\u5708\u7535\u611f\uff08\u6052\u5b9a\uff09");
    model.param("par3").set("C_MS", "2.51e-3[m/N]", "\u60ac\u67b6\u67d4\u6027");
    model.param("par3").set("R_MS", "12.9e-3[N*s/m]", "\u60ac\u67b6\u673a\u68b0\u635f\u8017");
    model.param("par3").set("M_MD", "314.9[ug]", "\u52a8\u8d28\u91cf\uff08\u97f3\u5708\u548c\u632f\u819c\uff09");
    model.param("par3")
         .set("BL", "4.56[T*m]", "\u529b\u56e0\u5b50\uff0c\u78c1\u901a\u5bc6\u5ea6 (B) \u4e58\u4ee5\u97f3\u5708\u957f\u5ea6 (L)");
    model.param("par3").set("V0", "200*sqrt(2)[mV]", "\u9a71\u52a8\u7535\u538b\uff08\u5cf0\u503c\uff09");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "headphone_artificial_ear_geometry.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u6ce1\u6cab\u68c9");
    model.component("comp1").selection("sel1").set(15, 16, 17, 18, 19, 20);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u52a8\u819c\u6b63\u6781");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(150, 152, 156, 158);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u52a8\u819c\u8d1f\u6781");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(151, 153, 157, 159);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u5185\u90e8\u786c\u58f0\u573a\u8fb9\u754c");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(126, 127, 128, 129, 146, 147, 148, 149, 154, 155, 160, 161);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u8033\u819c");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(333);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").label("\u542b PML \u7684\u76ae\u80a4");
    model.component("comp1").selection("sel6")
         .set(289, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 334, 335);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u4e0d\u542b PML \u7684\u76ae\u80a4");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(289, 317, 318, 319, 320, 321, 322, 324, 325, 326, 327, 328);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("PML \u4fa7\u9762");
    model.component("comp1").selection("sel8").set(3, 4, 22, 25);
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").label("PML \u62d0\u89d2");
    model.component("comp1").selection("sel9").set(1, 2, 5, 6, 21, 23, 24, 26);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").label("PML \u9876\u90e8");
    model.component("comp1").selection("sel10").set(7, 9);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").label("\u7a7f\u5b54\u677f 1");
    model.component("comp1").selection("sel11").geom(2);
    model.component("comp1").selection("sel11").set(32);
    model.component("comp1").selection().create("sel12", "Explicit");
    model.component("comp1").selection("sel12").label("\u7a7f\u5b54\u677f 2");
    model.component("comp1").selection("sel12").geom(2);
    model.component("comp1").selection("sel12").set(88, 95, 98, 113);
    model.component("comp1").selection().create("sel13", "Explicit");
    model.component("comp1").selection("sel13").label("\u7a7f\u5b54\u677f 3");
    model.component("comp1").selection("sel13").geom(2);
    model.component("comp1").selection("sel13").set(272, 273, 282, 283);
    model.component("comp1").selection().create("sel14", "Explicit");
    model.component("comp1").selection("sel14").label("\u6240\u6709\u57df");
    model.component("comp1").selection("sel14").all();
    model.component("comp1").selection().create("sel15", "Explicit");
    model.component("comp1").selection("sel15").label("\u5851\u6599\u5916\u58f3");
    model.component("comp1").selection("sel15").set(10, 12);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u542b PML \u7684\u7a7a\u6c14");
    model.component("comp1").selection("dif1").set("add", new String[]{"sel14"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel1", "sel15"});
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").label("\u4e0d\u542b PML \u7684\u7a7a\u6c14");
    model.component("comp1").selection("dif2").set("add", new String[]{"dif1"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"sel8", "sel9", "sel10"});
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u7a7a\u6c14\u8fb9\u754c");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel5", "sel6", "sel11", "sel12", "sel13"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u52a8\u819c");
    model.component("comp1").selection("uni2").set("entitydim", 2);
    model.component("comp1").selection("uni2").set("input", new String[]{"sel2", "sel3"});
    model.component("comp1").selection().create("dif3", "Difference");
    model.component("comp1").selection("dif3")
         .label("\u4e0d\u542b PML \u548c\u6ce1\u6cab\u68c9\u7684\u5df2\u5212\u5206\u7f51\u683c\u57df");
    model.component("comp1").selection("dif3").set("add", new String[]{"sel14"});
    model.component("comp1").selection("dif3")
         .set("subtract", new String[]{"sel1", "sel8", "sel9", "sel10", "sel15"});
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").label("PML");
    model.component("comp1").selection("uni3").set("input", new String[]{"sel8", "sel9", "sel10"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u6a21\u578b\u53d8\u91cf");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("L_E", "(L_e/(sin(n_e*pi/2)))*(cir.omega[s/rad])^(n_e-1)", "\u97f3\u5708\u7535\u611f\uff08\u9891\u7387\u76f8\u5173\uff09");
    model.component("comp1").variable("var1")
         .set("Rp_E", "(L_e/(cos(n_e*pi/2)))*(cir.omega[s/rad])^(n_e)[ohm/H]", "\u7535\u963b\uff08\u78c1\u7cfb\u7edf\u4e2d\u7684\u635f\u8017\uff09");

    model.component("comp1").physics().create("cir", "Circuit", "geom1");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "V0");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "R_g");
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "R_E");
    model.component("comp1").physics("cir").create("L1", "Inductor", -1);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 4, 1, 0);
    model.component("comp1").physics("cir").feature("L1").set("L", "L_E");
    model.component("comp1").physics("cir").create("R3", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 4, 1, 0);
    model.component("comp1").physics("cir").feature("R3").set("R", "Rp_E");
    model.component("comp1").physics("cir").create("L2", "Inductor", -1);
    model.component("comp1").physics("cir").feature("L2").setIndex("Connections", 6, 0, 0);
    model.component("comp1").physics("cir").feature("L2").setIndex("Connections", 7, 1, 0);
    model.component("comp1").physics("cir").feature("L2").set("L", "M_MD[H/kg]");
    model.component("comp1").physics("cir").create("H1", "CurrentVoltageSource", -1);
    model.component("comp1").physics("cir").feature("H1").setIndex("Connections", 4, 0, 0);
    model.component("comp1").physics("cir").feature("H1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("H1").set("device", "L2");
    model.component("comp1").physics("cir").feature("H1").set("gain", "BL[m/Wb*ohm]");
    model.component("comp1").physics("cir").create("H2", "CurrentVoltageSource", -1);
    model.component("comp1").physics("cir").feature("H2").setIndex("Connections", 6, 0, 0);
    model.component("comp1").physics("cir").feature("H2").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("H2").set("gain", "BL[m/Wb*ohm]");
    model.component("comp1").physics("cir").feature("H2").set("device", "R2");
    model.component("comp1").physics("cir").create("R4", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R4").setIndex("Connections", 7, 0, 0);
    model.component("comp1").physics("cir").feature("R4").set("R", "R_MS[ohm/kg*s]");
    model.component("comp1").physics("cir").create("C1", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("C1").setIndex("Connections", 8, 0, 0);
    model.component("comp1").physics("cir").feature("C1").set("C", "C_MS[F*N/m]");
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 9, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics("acpr").selection().set();
    model.component("comp1").physics("acpr").selection().named("dif1");
    model.component("comp1").physics("acpr").create("imp1", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp1").label("\u8033\u819c\u963b\u6297");
    model.component("comp1").physics("acpr").feature("imp1").selection().named("sel5");
    model.component("comp1").physics("acpr").feature("imp1").set("ImpedanceModel", "Physiological");
    model.component("comp1").physics("acpr").feature("imp1").set("PhysiologicalModel", "HumanEarDrum");
    model.component("comp1").physics("acpr").create("imp2", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp2").label("\u76ae\u80a4\u963b\u6297");
    model.component("comp1").physics("acpr").feature("imp2").selection().named("sel6");
    model.component("comp1").physics("acpr").feature("imp2").set("ImpedanceModel", "Physiological");
    model.component("comp1").physics("acpr").create("ishb1", "InteriorSoundHard", 2);
    model.component("comp1").physics("acpr").feature("ishb1").selection().named("sel4");
    model.component("comp1").physics("acpr").create("ipp1", "InteriorPerforatedPlate", 2);
    model.component("comp1").physics("acpr").feature("ipp1").selection().named("sel11");
    model.component("comp1").physics("acpr").feature("ipp1").set("dh", "dh1");
    model.component("comp1").physics("acpr").feature("ipp1").set("tp", "tp1");
    model.component("comp1").physics("acpr").feature("ipp1").set("porArea", "sigma1");
    model.component("comp1").physics("acpr").create("ipp2", "InteriorPerforatedPlate", 2);
    model.component("comp1").physics("acpr").feature("ipp2").selection().named("sel12");
    model.component("comp1").physics("acpr").feature("ipp2").set("dh", "dh2");
    model.component("comp1").physics("acpr").feature("ipp2").set("tp", "tp2");
    model.component("comp1").physics("acpr").feature("ipp2").set("porArea", "sigma2");
    model.component("comp1").physics("acpr").create("ipp3", "InteriorPerforatedPlate", 2);
    model.component("comp1").physics("acpr").feature("ipp3").selection().named("sel13");
    model.component("comp1").physics("acpr").feature("ipp3").set("dh", "dh3");
    model.component("comp1").physics("acpr").feature("ipp3").set("tp", "tp3");
    model.component("comp1").physics("acpr").feature("ipp3").set("porArea", "sigma3");
    model.component("comp1").physics("acpr").create("ilsb1", "InteriorLumpedSpeakerBoundary", 2);
    model.component("comp1").physics("acpr").feature("ilsb1").selection().named("uni2");
    model.component("comp1").physics("acpr").feature("ilsb1").set("SpeakerAxisDirection", "UserDefined");
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.acpr.ilsb1.V_cir");
    model.component("comp1").physics().create("pelw", "PoroelasticWavesSinglePhysics", "geom1");
    model.component("comp1").physics("pelw").selection().set();
    model.component("comp1").physics("pelw").selection().named("sel1");
    model.component("comp1").physics("pelw").create("pfix1", "Fixed", 2);
    model.component("comp1").physics("pelw").feature("pfix1").selection()
         .set(260, 262, 265, 274, 278, 284, 318, 319, 320, 321, 322, 326);

    model.component("comp1").multiphysics().create("apb1", "AcousticPorousBoundary", 2);
    model.component("comp1").multiphysics("apb1").selection().all();

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("sel8");
    model.component("comp1").coordSystem("pml1").set("ScalingType", "userDefined");
    model.component("comp1").coordSystem("pml1").setIndex("d", "sqrt((x-40[mm])^2+(y-50[mm])^2)-60[mm]", 0);
    model.component("comp1").coordSystem("pml1").setIndex("dmax", "5[mm]", 0);
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "3");
    model.component("comp1").coordSystem().create("pml2", "PML");
    model.component("comp1").coordSystem("pml2").selection().named("sel10");
    model.component("comp1").coordSystem("pml2").set("ScalingType", "userDefined");
    model.component("comp1").coordSystem("pml2").setIndex("d", "abs(z)-65[mm]", 0);
    model.component("comp1").coordSystem("pml2").setIndex("dmax", "5[mm]", 0);
    model.component("comp1").coordSystem("pml2").set("PMLgamma", "3");
    model.component("comp1").coordSystem().create("pml3", "PML");
    model.component("comp1").coordSystem("pml3").selection().named("sel9");
    model.component("comp1").coordSystem("pml3").set("ScalingType", "userDefined");
    model.component("comp1").coordSystem("pml3").set("directions", "2");
    model.component("comp1").coordSystem("pml3").setIndex("d", "sqrt((x-40[mm])^2+(y-50[mm])^2)-60[mm]", 0);
    model.component("comp1").coordSystem("pml3").setIndex("dmax", "5[mm]", 0);
    model.component("comp1").coordSystem("pml3").setIndex("d", "abs(z)-65[mm]", 1);
    model.component("comp1").coordSystem("pml3").setIndex("dmax", "5[mm]", 1);
    model.component("comp1").coordSystem("pml3").set("PMLgamma", "3");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14 - \u57df");
    model.component("comp1").material("mat1").selection().named("dif1");

    model.component("comp1").physics("pelw").feature("pm1").set("FluidMaterial", "mat1");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").label("Air");
    model.component("comp1").material("mat2").set("family", "air");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat2").materialType("nonSolid");
    model.component("comp1").material("mat2").label("\u7a7a\u6c14 - \u8fb9\u754c");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("uni1");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u6ce1\u6cab\u68c9");
    model.component("comp1").material("mat3").selection().named("sel1");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat3").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat3").propertyGroup("KG").set("G", new String[]{"500[kPa]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"30[kg/m^3]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("porosity", new String[]{"0.85"});
    model.component("comp1").material("mat3").propertyGroup()
         .create("PoroacousticsModel", "PoroacousticsModel", "Poroacoustics_model");
    model.component("comp1").material("mat3").propertyGroup("PoroacousticsModel").set("tau", new String[]{"1.18"});
    model.component("comp1").material("mat3").propertyGroup("PoroacousticsModel")
         .set("Rf", new String[]{"34000[N*s/m^4]"});
    model.component("comp1").material("mat3").propertyGroup("PoroacousticsModel").set("Lv", new String[]{"60[um]"});
    model.component("comp1").material("mat3").propertyGroup("PoroacousticsModel").set("Lth", new String[]{"87[um]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("lossfactor", new String[]{"0.015"});

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lambda_air/5");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "1[mm]");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.4);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.5);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 1);
    model.component("comp1").mesh("mesh1").run("size");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(261, 264, 267, 276, 281, 288);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "lambda_poro/7.5");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "lambda_poro/7.5");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("dif3");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "2.0[mm]");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().named("uni3");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/pelw", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/apb1", true);
    model.study("std1").feature("freq")
         .set("plist", "{20, 25, 31.5, 40, 50, 63, 80, 100, 125, 160, 200, 250, 315, 400, 500, 630, 800, 1e3, 1.25e3, 1.6e3, 2e3, 2.5e3, 3.15e3, 4e3, 5e3, 6.3e3, 8e3, 1e4, 1.25e4, 1.6e4, 2e4}");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 31, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 31, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 31, 0);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u4f4d\u79fb (pelw)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "pelw.disp");
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("def1", "Deform");
    model.result("pg4").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg4").feature("surf1").feature("def1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", "-10[mm]");
    model.result().dataset("cpt1").set("pointy", "50[mm]");
    model.result().dataset("cpt1").set("pointz", 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().remove("surf1");
    model.result("pg1").run();
    model.result("pg1").selection().geom("geom1", 3);
    model.result("pg1").selection().geom("geom1", 3);
    model.result("pg1").selection().set(8, 11, 13, 14, 15, 16, 17, 18, 19, 20);
    model.result("pg1").set("applyselectiontodatasetedges", true);
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", "if(isnan(acpr.p_t),pelw.p_t,acpr.p_t)");
    model.result("pg1").feature("mslc1").set("descractive", true);
    model.result("pg1").feature("mslc1").set("descr", "\u603b\u58f0\u538b");
    model.result("pg1").run();
    model.result("pg2").set("applyselectiontodatasetedges", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().remove("surf1");
    model.result("pg2").run();
    model.result("pg2").selection().geom("geom1", 3);
    model.result("pg2").selection().geom("geom1", 3);
    model.result("pg2").selection().set(8, 11, 13, 14, 15, 16, 17, 18, 19, 20);
    model.result("pg2").set("applyselectiontodatasetedges", true);
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "if(isnan(acpr.Lp),pelw.Lp_t,acpr.Lp)");
    model.result("pg2").feature("mslc1").set("descractive", true);
    model.result("pg2").feature("mslc1").set("descr", "\u603b\u58f0\u538b\u7ea7");
    model.result("pg2").feature("mslc1").set("colortable", "Rainbow");
    model.result("pg2").feature("mslc1").set("colorscalemode", "linear");
    model.result("pg2").feature("mslc1").set("resolution", "finer");
    model.result("pg2").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 31, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").label("\u58f0\u538b\u7ea7 (acpr) 1");
    model.result("pg5").set("applyselectiontodatasetedges", false);
    model.result("pg5").run();
    model.result("pg5").label("\u4eba\u4f53\u8868\u9762\u7684\u58f0\u538b\u7ea7");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u58f0\u538b\u7ea7 (dB)");
    model.result("pg5").set("paramindicator", "freq=eval(freq) Hz");
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().named("sel7");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf2", "surf1");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("expr", "pelw.Lp");
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").setIndex("looplevel", 11, 0);
    model.result("pg5").setIndex("looplevel", 21, 0);
    model.result("pg5").setIndex("looplevel", 31, 0);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u58f0\u538b\u7ea7");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "f(Hz)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "SPL (dB)");
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").create("oct1", "OctaveBand");
    model.result("pg6").feature("oct1").set("quantity", "bandpower");
    model.result("pg6").feature("oct1").set("markerpos", "datapoints");
    model.result("pg6").feature("oct1").set("linewidth", "preference");
    model.result("pg6").feature("oct1").label("\u8033\u819c\u5904\u7684\u5e73\u5747 SPL");
    model.result("pg6").feature("oct1").selection().geom("geom1", 2);
    model.result("pg6").feature("oct1").selection().named("sel5");
    model.result("pg6").feature("oct1").set("quantity", "continuous");
    model.result("pg6").run();
    model.result("pg6").feature("oct1").set("legend", true);
    model.result("pg6").feature("oct1").set("autoplotlabel", true);
    model.result("pg6").feature("oct1").set("autosolution", false);
    model.result("pg6").feature("oct1").set("autodescr", false);
    model.result("pg6").run();
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").label("\u5916\u90e8\u70b9");
    model.result("pg6").feature("ptgr1").set("data", "cpt1");
    model.result("pg6").feature("ptgr1").set("expr", "acpr.Lp");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("autoplotlabel", true);
    model.result("pg6").feature("ptgr1").set("autopoint", false);
    model.result("pg6").feature("ptgr1").set("autosolution", false);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u5b8c\u7f8e\u5339\u914d\u5c42\u8ddd\u79bb\u51fd\u6570");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").set("expr", "pml1.dDist");
    model.result("pg7").feature("mslc1").set("colortable", "Rainbow");
    model.result("pg7").feature("mslc1").set("colorscalemode", "linear");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("mslc2", "mslc1");
    model.result("pg7").run();
    model.result("pg7").feature("mslc2").set("expr", "pml2.dDist");
    model.result("pg7").feature("mslc2").set("titletype", "none");
    model.result("pg7").feature("mslc2").set("inheritplot", "mslc1");
    model.result("pg7").feature().duplicate("mslc3", "mslc2");
    model.result("pg7").run();
    model.result("pg7").feature("mslc3").set("expr", "sqrt(pml3.dDist1^2+pml3.dDist2^2)");
    model.result("pg7").run();
    model.result("pg5").run();

    model.title("\u6234\u5728\u4eff\u771f\u4eba\u8033\u4e0a\u7684\u8033\u673a");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5728\u5178\u578b\u7684\u6d4b\u91cf\u8bbe\u7f6e\u4e2d\u6a21\u62df\u8033\u673a\u3002\u7531\u4e8e\u8033\u673a\u4e0e\u4eba\u8033\u7d27\u5bc6\u8026\u5408\uff0c\u56e0\u6b64\u5728\u4f20\u7edf\u7684\u626c\u58f0\u5668\u6a21\u578b\u8bbe\u7f6e\u7684\u58f0\u81ea\u7531\u573a\u4e2d\u6d4b\u91cf\u8033\u673a\u7684\u7075\u654f\u5ea6\u5e76\u4e0d\u5177\u6709\u4ee3\u8868\u6027\u3002\u6d4b\u91cf\u65f6\u9700\u8981\u4f7f\u7528\u4eff\u771f\u4eba\u5934\u548c\u4eff\u771f\u4eba\u8033\u6765\u51c6\u786e\u8868\u793a\u8033\u673a\u7684\u4f7f\u7528\u60c5\u51b5\u3002\u6b64\u6a21\u578b\u6f14\u793a\u4e00\u4e2a\u5168\u7f69\u5f0f\u8033\u673a\u4e0e\u901a\u7528\u4eff\u771f\u4eba\u8033\u7684\u8026\u5408\u5206\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("headphone_artificial_ear.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
