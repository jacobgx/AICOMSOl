/*
 * compact_camera_module_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:16 by COMSOL 6.3.0.290. */
public class compact_camera_module_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Lenses_Cameras_and_Telescopes");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").label("\u5fae\u578b\u76f8\u673a\u6a21\u7ec4\u51e0\u4f55\u5e8f\u5217");
    model.component("comp1").geom("geom1").lengthUnit("mm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");

    model.disableUpdates(true);

    model.param().set("nix", "0", "\u5168\u5c40\u5149\u8f74\uff0cx \u5206\u91cf");
    model.param().set("niy", "0", "\u5168\u5c40\u5149\u8f74\uff0cy \u5206\u91cf");
    model.param().set("niz", "1", "\u5168\u5c40\u5149\u8f74\uff0cz \u5206\u91cf");
    model.param().set("A_norm", "1[mm]", "\u975e\u7403\u9762\u5f52\u4e00\u5316\u5355\u5143");
    model.param().set("d0_S", "5.000[mm]", "\u5149\u9611\uff0c\u5916\u5f84");
    model.param().set("d1_S", "2.505[mm]", "\u5149\u9611\uff0c\u5185\u5f84\uff08\u8d85\u5927\uff09");
    model.param().set("R1_1", "1.679[mm]", "\u900f\u955c 1\uff0c\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 1");
    model.param().set("R2_1", "-9.162[mm]", "\u900f\u955c 1\uff0c\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 2");
    model.param().set("K1_1", "0.22669364", "\u900f\u955c 1\uff0c\u5706\u9525\u5e38\u6570\uff0c\u8868\u9762 1");
    model.param().set("K2_1", "0", "\u900f\u955c 1\uff0c\u5706\u9525\u5e38\u6570\uff0c\u8868\u9762 1");
    model.param()
         .set("A1_1", "9.80281e-03", "\u900f\u955c 1\uff0c\u7b2c 4 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("B1_1", "-3.81227e-02", "\u900f\u955c 1\uff0c\u7b2c 6 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("C1_1", "2.39681e-02", "\u900f\u955c 1\uff0c\u7b2c 8 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("D1_1", "-6.29128e-03", "\u900f\u955c 1\uff0c\u7b2c 10 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("E1_1", "-2.75496e-03", "\u900f\u955c 1\uff0c\u7b2c 12 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("F1_1", "-2.69638e-04", "\u900f\u955c 1\uff0c\u7b2c 14 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("A2_1", "3.73187e-02", "\u900f\u955c 1\uff0c\u7b2c 4 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("B2_1", "-8.91760e-03", "\u900f\u955c 1\uff0c\u7b2c 6 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("C2_1", "-5.89384e-02", "\u900f\u955c 1\uff0c\u7b2c 8 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("D2_1", "4.41115e-02", "\u900f\u955c 1\uff0c\u7b2c 10 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("E2_1", "-1.26858e-02", "\u900f\u955c 1\uff0c\u7b2c 12 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("F2_1", "1.16125e-03", "\u900f\u955c 1\uff0c\u7b2c 14 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param().set("Tc_1", "1.1080[mm]", "\u900f\u955c 1\uff0c\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("d0_1", "3.05[mm]", "\u900f\u955c 1\uff0c\u5916\u5f84");
    model.param().set("d1_1", "2.85[mm]", "\u900f\u955c 1\uff0c\u8868\u9762 1 \u76f4\u5f84");
    model.param().set("d2_1", "0", "\u900f\u955c 1\uff0c\u8868\u9762 2 \u76f4\u5f84");
    model.param().set("T_1", "0.1000[mm]", "\u900f\u955c 1 \u5230\u900f\u955c 2 \u7684\u8ddd\u79bb");
    model.param().set("R1_2", "-15.649[mm]", "\u900f\u955c 2\uff0c\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 1");
    model.param().set("R2_2", "3.482[mm]", "\u900f\u955c 2\uff0c\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 2");
    model.param().set("K1_2", "0", "\u900f\u955c 2\uff0c\u5706\u9525\u5e38\u6570\uff0c\u8868\u9762 1");
    model.param().set("K2_2", "8.70133393", "\u900f\u955c 2\uff0c\u5706\u9525\u5e38\u6570\uff0c\u8868\u9762 1");
    model.param()
         .set("A1_2", "6.93172e-02", "\u900f\u955c 2\uff0c\u7b2c 4 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("B1_2", "-4.31157e-02", "\u900f\u955c 2\uff0c\u7b2c 6 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("C1_2", "2.33346e-02", "\u900f\u955c 2\uff0c\u7b2c 8 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("D1_2", "-2.33074e-02", "\u900f\u955c 2\uff0c\u7b2c 10 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("E1_2", "2.22119e-02", "\u900f\u955c 2\uff0c\u7b2c 12 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("F1_2", "-4.84076e-03", "\u900f\u955c 2\uff0c\u7b2c 14 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("A2_2", "5.21579e-03", "\u900f\u955c 2\uff0c\u7b2c 4 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("B2_2", "7.15829e-02", "\u900f\u955c 2\uff0c\u7b2c 6 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("C2_2", "-4.60926e-02", "\u900f\u955c 2\uff0c\u7b2c 8 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("D2_2", "1.24310e-02", "\u900f\u955c 2\uff0c\u7b2c 10 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("E2_2", "3.32216e-02", "\u900f\u955c 2\uff0c\u7b2c 12 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("F2_2", "0", "\u900f\u955c 2\uff0c\u7b2c 14 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param().set("Tc_2", "0.2300[mm]", "\u900f\u955c 2\uff0c\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("d0_2", "2.70[mm]", "\u900f\u955c 2\uff0c\u5916\u5f84");
    model.param().set("d1_2", "0", "\u900f\u955c 2\uff0c\u8868\u9762 1 \u76f4\u5f84");
    model.param().set("d2_2", "2.21[mm]", "\u900f\u955c 2\uff0c\u8868\u9762 2 \u76f4\u5f84");
    model.param().set("T_2", "1.1305[mm]", "\u900f\u955c 2 \u5230\u900f\u955c 3 \u7684\u8ddd\u79bb");
    model.param().set("R1_3", "-12.801[mm]", "\u900f\u955c 3\uff0c\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 1");
    model.param().set("R2_3", "21.119[mm]", "\u900f\u955c 3\uff0c\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 2");
    model.param().set("K1_3", "0", "\u900f\u955c 3\uff0c\u5706\u9525\u5e38\u6570\uff0c\u8868\u9762 1");
    model.param().set("K2_3", "0", "\u900f\u955c 3\uff0c\u5706\u9525\u5e38\u6570\uff0c\u8868\u9762 1");
    model.param()
         .set("A1_3", "3.96000e-02", "\u900f\u955c 3\uff0c\u7b2c 4 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("B1_3", "-3.42179e-02", "\u900f\u955c 3\uff0c\u7b2c 6 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("C1_3", "7.75523e-02", "\u900f\u955c 3\uff0c\u7b2c 8 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("D1_3", "-4.22361e-02", "\u900f\u955c 3\uff0c\u7b2c 10 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("E1_3", "0", "\u900f\u955c 3\uff0c\u7b2c 12 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("F1_3", "0", "\u900f\u955c 3\uff0c\u7b2c 14 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("A2_3", "1.01117e-01", "\u900f\u955c 3\uff0c\u7b2c 4 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("B2_3", "-3.21118e-02", "\u900f\u955c 3\uff0c\u7b2c 6 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("C2_3", "9.03668e-02", "\u900f\u955c 3\uff0c\u7b2c 8 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("D2_3", "-3.37156e-02", "\u900f\u955c 3\uff0c\u7b2c 10 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("E2_3", "-6.52751e-03", "\u900f\u955c 3\uff0c\u7b2c 12 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("F2_3", "0", "\u900f\u955c 3\uff0c\u7b2c 14 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param().set("Tc_3", "0.2300[mm]", "\u900f\u955c 3\uff0c\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("d0_3", "2.50[mm]", "\u900f\u955c 3\uff0c\u5916\u5f84");
    model.param().set("d1_3", "2.30[mm]", "\u900f\u955c 3\uff0c\u8868\u9762 1 \u76f4\u5f84");
    model.param().set("d2_3", "2.25[mm]", "\u900f\u955c 3\uff0c\u8868\u9762 2 \u76f4\u5f84");
    model.param().set("T_3", "1.0559[mm]", "\u900f\u955c 3 \u5230\u900f\u955c 4 \u7684\u8ddd\u79bb");
    model.param().set("R1_4", "-3.266[mm]", "\u900f\u955c 4\uff0c\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 1");
    model.param().set("R2_4", "2.724[mm]", "\u900f\u955c 4\uff0c\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 2");
    model.param().set("K1_4", "0.85965815", "\u900f\u955c 4\uff0c\u5706\u9525\u5e38\u6570\uff0c\u8868\u9762 1");
    model.param().set("K2_4", "0", "\u900f\u955c 4\uff0c\u5706\u9525\u5e38\u6570\uff0c\u8868\u9762 1");
    model.param()
         .set("A1_4", "-4.91398e-02", "\u900f\u955c 4\uff0c\u7b2c 4 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("B1_4", "-5.57533e-03", "\u900f\u955c 4\uff0c\u7b2c 6 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("C1_4", "1.31557e-02", "\u900f\u955c 4\uff0c\u7b2c 8 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("D1_4", "1.22280e-03", "\u900f\u955c 4\uff0c\u7b2c 10 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("E1_4", "-9.54019e-04", "\u900f\u955c 4\uff0c\u7b2c 12 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("F1_4", "-2.40349e-06", "\u900f\u955c 4\uff0c\u7b2c 14 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("A2_4", "-8.88955e-02", "\u900f\u955c 4\uff0c\u7b2c 4 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("B2_4", "2.87927e-02", "\u900f\u955c 4\uff0c\u7b2c 6 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("C2_4", "-8.83436e-03", "\u900f\u955c 4\uff0c\u7b2c 8 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("D2_4", "1.57329e-03", "\u900f\u955c 4\uff0c\u7b2c 10 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("E2_4", "-2.24134e-04", "\u900f\u955c 4\uff0c\u7b2c 12 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("F2_4", "0", "\u900f\u955c 4\uff0c\u7b2c 14 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param().set("Tc_4", "0.2300[mm]", "\u900f\u955c 4\uff0c\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("d0_4", "3.75[mm]", "\u900f\u955c 4\uff0c\u5916\u5f84");
    model.param().set("d1_4", "2.95[mm]", "\u900f\u955c 4\uff0c\u8868\u9762 1 \u76f4\u5f84");
    model.param().set("d2_4", "0", "\u900f\u955c 4\uff0c\u8868\u9762 2 \u76f4\u5f84");
    model.param().set("T_4", "0.1000[mm]", "\u900f\u955c 4 \u5230\u900f\u955c 5 \u7684\u8ddd\u79bb");
    model.param().set("R1_5", "5.272[mm]", "\u900f\u955c 5\uff0c\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 1");
    model.param().set("R2_5", "-4.681[mm]", "\u900f\u955c 5\uff0c\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 2");
    model.param().set("K1_5", "0", "\u900f\u955c 5\uff0c\u5706\u9525\u5e38\u6570\uff0c\u8868\u9762 1");
    model.param().set("K2_5", "3.15790955", "\u900f\u955c 5\uff0c\u5706\u9525\u5e38\u6570\uff0c\u8868\u9762 1");
    model.param()
         .set("A1_5", "-2.38313e-02", "\u900f\u955c 5\uff0c\u7b2c 4 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("B1_5", "5.50321e-03", "\u900f\u955c 5\uff0c\u7b2c 6 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("C1_5", "-9.19080e-04", "\u900f\u955c 5\uff0c\u7b2c 8 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("D1_5", "-9.80631e-05", "\u900f\u955c 5\uff0c\u7b2c 10 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("E1_5", "0", "\u900f\u955c 5\uff0c\u7b2c 12 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("F1_5", "0", "\u900f\u955c 5\uff0c\u7b2c 14 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 1");
    model.param()
         .set("A2_5", "-3.17139e-02", "\u900f\u955c 5\uff0c\u7b2c 4 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("B2_5", "3.80781e-03", "\u900f\u955c 5\uff0c\u7b2c 6 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("C2_5", "3.43810e-04", "\u900f\u955c 5\uff0c\u7b2c 8 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("D2_5", "-3.27888e-05", "\u900f\u955c 5\uff0c\u7b2c 10 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("E2_5", "0", "\u900f\u955c 5\uff0c\u7b2c 12 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("F2_5", "0", "\u900f\u955c 5\uff0c\u7b2c 14 \u7ea7\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param().set("Tc_5", "1.0356[mm]", "\u900f\u955c 5\uff0c\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("d0_5", "4.00[mm]", "\u900f\u955c 5\uff0c\u5916\u5f84");
    model.param().set("d1_5", "3.90[mm]", "\u900f\u955c 5\uff0c\u8868\u9762 1 \u76f4\u5f84");
    model.param().set("d2_5", "0", "\u900f\u955c 5\uff0c\u8868\u9762 2 \u76f4\u5f84");
    model.param().set("T_5", "0.1337[mm]", "\u900f\u955c 5 \u5230\u6ee4\u955c\u7684\u8ddd\u79bb");
    model.param().set("Tc_F", "0.2100[mm]", "\u7ea2\u5916\u6ee4\u955c\uff0c\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("d0_F", "4.50[mm]", "\u7ea2\u5916\u6ee4\u955c\uff0c\u5916\u5f84");
    model.param().set("T_6", "0.1363[mm]", "\u7ea2\u5916\u6ee4\u955c\u5230\u50cf\u5e73\u9762\u7684\u8ddd\u79bb");
    model.param().set("d0_I", "5.00[mm]", "\u50cf\u5e73\u9762\u76f4\u5f84");

    model.disableUpdates(false);

    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Apertures_and_Obstructions\\circular_planar_annulus.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").label("\u5149\u9611");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d0", "d0_S");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1", "d1_S");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nix", "nix");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niy", "niy");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niz", "niz");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel1", true);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u975e\u7403\u9762");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel1", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u969c\u788d\u7269");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel1", "csel2");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u5168\u90e8\u5916\u90e8");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel1", "csel3");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel1", "csel2");
    model.geom()
         .load(new String[]{"part2"}, "Ray_Optics_Module\\3D\\Aspheric_Lenses\\aspheric_even_lens_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").label("\u900f\u955c 1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "R1", "R1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "R2", "R2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "k1", "k1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "k2", "k2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A_norm", "A_norm");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A02_1", "A02_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A04_1", "A04_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A06_1", "A06_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A08_1", "A08_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A10_1", "A10_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A12_1", "A12_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A14_1", "A14_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A16_1", "A16_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A18_1", "A18_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A20_1", "A20_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A22_1", "A22_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A02_2", "A02_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A04_2", "A04_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A06_2", "A06_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A08_2", "A08_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A10_2", "A10_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A12_2", "A12_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A14_2", "A14_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A16_2", "A16_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A18_2", "A18_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A20_2", "A20_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "A22_2", "A22_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "Tc", "Tc");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "d0", "d0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "d1", "d1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "d2", "d2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "d1_clear", "d1_clear");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "d2_clear", "d2_clear");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "nix", "nix");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "niy", "niy");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "niz", "niz");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "n_extra_r", "n_extra_r");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputname", "n_extra_a", "n_extra_a");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "R1", "R1_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "R2", "R2_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "k1", "K1_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "k2", "K2_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A_norm", "A_norm");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A02_1", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A04_1", "A1_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A06_1", "B1_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A08_1", "C1_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A10_1", "D1_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A12_1", "E1_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A14_1", "F1_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A16_1", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A18_1", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A20_1", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A22_1", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A02_2", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A04_2", "A2_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A06_2", "B2_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A08_2", "C2_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A10_2", "D2_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A12_2", "E2_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A14_2", "F2_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A16_2", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A18_2", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A20_2", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "A22_2", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "Tc", "Tc_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d0", "d0_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d1", "d1_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d2", "d2_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d1_clear", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d2_clear", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "nix", "nix");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "niy", "niy");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "niz", "niz");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "n_extra_r", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "n_extra_a", "0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_sel2", "csel3");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_sel3", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_sel4", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_sel7", "csel2");
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi3").label("\u900f\u955c 2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "R1", "R1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "R2", "R2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "k1", "k1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "k2", "k2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A_norm", "A_norm");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A02_1", "A02_1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A04_1", "A04_1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A06_1", "A06_1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A08_1", "A08_1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A10_1", "A10_1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A12_1", "A12_1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A14_1", "A14_1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A16_1", "A16_1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A18_1", "A18_1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A20_1", "A20_1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A22_1", "A22_1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A02_2", "A02_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A04_2", "A04_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A06_2", "A06_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A08_2", "A08_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A10_2", "A10_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A12_2", "A12_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A14_2", "A14_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A16_2", "A16_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A18_2", "A18_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A20_2", "A20_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "A22_2", "A22_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "Tc", "Tc");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "d0", "d0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "d1", "d1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "d2", "d2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "d1_clear", "d1_clear");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "d2_clear", "d2_clear");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "nix", "nix");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "niy", "niy");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "niz", "niz");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "n_extra_r", "n_extra_r");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputname", "n_extra_a", "n_extra_a");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "R1", "R1_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "R2", "R2_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "k1", "K1_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "k2", "K2_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A_norm", "A_norm");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A02_1", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A04_1", "A1_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A06_1", "B1_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A08_1", "C1_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A10_1", "D1_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A12_1", "E1_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A14_1", "F1_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A16_1", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A18_1", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A20_1", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A22_1", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A02_2", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A04_2", "A2_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A06_2", "B2_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A08_2", "C2_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A10_2", "D2_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A12_2", "E2_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A14_2", "F2_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A16_2", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A18_2", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A20_2", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "A22_2", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "Tc", "Tc_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d0", "d0_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d1", "d1_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d2", "d2_2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d1_clear", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d2_clear", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "nix", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "niy", "0");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "niz", "1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "n_extra_r", "0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "n_extra_a", "0");
    model.component("comp1").geom("geom1").feature("pi3").set("workplanesrc", "pi2");
    model.component("comp1").geom("geom1").feature("pi3").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("pi3").set("displ", new String[]{"0", "0", "T_1"});
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_sel2", "csel3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_sel3", "csel1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_sel4", "csel1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_sel7", "csel2");
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi4").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi4").label("\u900f\u955c 3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "R1", "R1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "R2", "R2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "k1", "k1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "k2", "k2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A_norm", "A_norm");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A02_1", "A02_1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A04_1", "A04_1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A06_1", "A06_1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A08_1", "A08_1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A10_1", "A10_1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A12_1", "A12_1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A14_1", "A14_1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A16_1", "A16_1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A18_1", "A18_1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A20_1", "A20_1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A22_1", "A22_1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A02_2", "A02_2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A04_2", "A04_2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A06_2", "A06_2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A08_2", "A08_2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A10_2", "A10_2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A12_2", "A12_2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A14_2", "A14_2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A16_2", "A16_2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A18_2", "A18_2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A20_2", "A20_2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "A22_2", "A22_2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "Tc", "Tc");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "d0", "d0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "d1", "d1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "d2", "d2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "d1_clear", "d1_clear");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "d2_clear", "d2_clear");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "nix", "nix");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "niy", "niy");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "niz", "niz");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "n_extra_r", "n_extra_r");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputname", "n_extra_a", "n_extra_a");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "R1", "R1_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "R2", "R2_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "k1", "K1_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "k2", "K2_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A_norm", "A_norm");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A02_1", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A04_1", "A1_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A06_1", "B1_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A08_1", "C1_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A10_1", "D1_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A12_1", "E1_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A14_1", "F1_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A16_1", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A18_1", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A20_1", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A22_1", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A02_2", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A04_2", "A2_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A06_2", "B2_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A08_2", "C2_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A10_2", "D2_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A12_2", "E2_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A14_2", "F2_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A16_2", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A18_2", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A20_2", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "A22_2", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "Tc", "Tc_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "d0", "d0_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "d1", "d1_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "d2", "d2_3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "d1_clear", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "d2_clear", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "nix", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "niy", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "niz", "1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "n_extra_r", "0");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "n_extra_a", "0");
    model.component("comp1").geom("geom1").feature("pi4").set("workplanesrc", "pi3");
    model.component("comp1").geom("geom1").feature("pi4").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("pi4").set("displ", new String[]{"0", "0", "T_2"});
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selcontributetobnd", "pi4_sel2", "csel3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selcontributetobnd", "pi4_sel3", "csel1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selcontributetobnd", "pi4_sel4", "csel1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selcontributetobnd", "pi4_sel7", "csel2");
    model.component("comp1").geom("geom1").run("pi4");
    model.component("comp1").geom("geom1").create("pi5", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi5").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi5").label("\u900f\u955c 4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "R1", "R1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "R2", "R2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "k1", "k1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "k2", "k2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A_norm", "A_norm");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A02_1", "A02_1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A04_1", "A04_1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A06_1", "A06_1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A08_1", "A08_1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A10_1", "A10_1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A12_1", "A12_1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A14_1", "A14_1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A16_1", "A16_1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A18_1", "A18_1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A20_1", "A20_1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A22_1", "A22_1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A02_2", "A02_2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A04_2", "A04_2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A06_2", "A06_2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A08_2", "A08_2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A10_2", "A10_2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A12_2", "A12_2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A14_2", "A14_2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A16_2", "A16_2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A18_2", "A18_2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A20_2", "A20_2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "A22_2", "A22_2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "Tc", "Tc");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "d0", "d0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "d1", "d1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "d2", "d2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "d1_clear", "d1_clear");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "d2_clear", "d2_clear");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "nix", "nix");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "niy", "niy");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "niz", "niz");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "n_extra_r", "n_extra_r");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputname", "n_extra_a", "n_extra_a");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "R1", "R1_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "R2", "R2_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "k1", "K1_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "k2", "K2_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A_norm", "A_norm");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A02_1", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A04_1", "A1_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A06_1", "B1_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A08_1", "C1_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A10_1", "D1_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A12_1", "E1_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A14_1", "F1_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A16_1", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A18_1", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A20_1", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A22_1", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A02_2", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A04_2", "A2_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A06_2", "B2_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A08_2", "C2_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A10_2", "D2_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A12_2", "E2_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A14_2", "F2_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A16_2", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A18_2", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A20_2", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "A22_2", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "Tc", "Tc_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "d0", "d0_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "d1", "d1_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "d2", "d2_4");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "d1_clear", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "d2_clear", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "nix", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "niy", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "niz", "1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "n_extra_r", "0");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "n_extra_a", "0");
    model.component("comp1").geom("geom1").feature("pi5").set("workplanesrc", "pi4");
    model.component("comp1").geom("geom1").feature("pi5").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("pi5").set("displ", new String[]{"0", "0", "T_3"});
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selcontributetobnd", "pi5_sel2", "csel3");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selcontributetobnd", "pi5_sel3", "csel1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selcontributetobnd", "pi5_sel4", "csel1");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selcontributetobnd", "pi5_sel7", "csel2");
    model.component("comp1").geom("geom1").run("pi5");
    model.component("comp1").geom("geom1").create("pi6", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi6").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi6").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi6").label("\u900f\u955c 5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "R1", "R1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "R2", "R2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "k1", "k1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "k2", "k2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A_norm", "A_norm");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A02_1", "A02_1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A04_1", "A04_1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A06_1", "A06_1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A08_1", "A08_1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A10_1", "A10_1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A12_1", "A12_1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A14_1", "A14_1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A16_1", "A16_1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A18_1", "A18_1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A20_1", "A20_1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A22_1", "A22_1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A02_2", "A02_2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A04_2", "A04_2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A06_2", "A06_2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A08_2", "A08_2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A10_2", "A10_2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A12_2", "A12_2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A14_2", "A14_2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A16_2", "A16_2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A18_2", "A18_2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A20_2", "A20_2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "A22_2", "A22_2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "Tc", "Tc");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "d0", "d0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "d1", "d1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "d2", "d2");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "d1_clear", "d1_clear");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "d2_clear", "d2_clear");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "nix", "nix");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "niy", "niy");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "niz", "niz");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "n_extra_r", "n_extra_r");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputname", "n_extra_a", "n_extra_a");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "R1", "R1_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "R2", "R2_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "k1", "K1_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "k2", "K2_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A_norm", "A_norm");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A02_1", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A04_1", "A1_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A06_1", "B1_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A08_1", "C1_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A10_1", "D1_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A12_1", "E1_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A14_1", "F1_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A16_1", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A18_1", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A20_1", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A22_1", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A02_2", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A04_2", "A2_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A06_2", "B2_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A08_2", "C2_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A10_2", "D2_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A12_2", "E2_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A14_2", "F2_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A16_2", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A18_2", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A20_2", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "A22_2", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "Tc", "Tc_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "d0", "d0_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "d1", "d1_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "d2", "d2_5");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "d1_clear", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "d2_clear", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "nix", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "niy", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "niz", "1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "n_extra_r", "0");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "n_extra_a", "0");
    model.component("comp1").geom("geom1").feature("pi6").set("workplanesrc", "pi5");
    model.component("comp1").geom("geom1").feature("pi6").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("pi6").set("displ", new String[]{"0", "0", "T_4"});
    model.component("comp1").geom("geom1").feature("pi6").setEntry("selcontributetobnd", "pi6_sel2", "csel3");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("selcontributetobnd", "pi6_sel3", "csel1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("selcontributetobnd", "pi6_sel4", "csel1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("selcontributetobnd", "pi6_sel7", "csel2");
    model.geom()
         .load(new String[]{"part3"}, "Ray_Optics_Module\\3D\\Spherical_Lenses\\spherical_lens_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi6");
    model.component("comp1").geom("geom1").create("pi7", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi7").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi7").label("\u7ea2\u5916\u6ee4\u955c");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "R1", 0);
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "R2", 0);
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "Tc", "Tc_F");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "d0", "d0_F");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "d2", 0);
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "d1_clear", 0);
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "d2_clear", 0);
    model.component("comp1").geom("geom1").feature("pi7").set("workplanesrc", "pi6");
    model.component("comp1").geom("geom1").feature("pi7").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("pi7").set("displ", new String[]{"0", "0", "T_5"});
    model.component("comp1").geom("geom1").feature("pi7").setEntry("selcontributetobnd", "pi7_sel2", "csel3");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("selcontributetobnd", "pi7_sel7", "csel2");
    model.component("comp1").geom("geom1").run("pi7");
    model.component("comp1").geom("geom1").create("pi8", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi8").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi8").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi8").label("\u50cf\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "d0", "d0_I");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi8").set("workplanesrc", "pi7");
    model.component("comp1").geom("geom1").feature("pi8").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("pi8").set("displ", new String[]{"0", "0", "T_6"});
    model.component("comp1").geom("geom1").feature("pi8").setEntry("selkeepbnd", "pi8_sel1", true);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("compact_camera_module_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
